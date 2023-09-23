package com.example.ecommercebe.service.product;

import com.example.ecommercebe.entity.Category;
import com.example.ecommercebe.entity.Product;
import com.example.ecommercebe.entity.Size;
import com.example.ecommercebe.exception.ProductException;
import com.example.ecommercebe.repository.CategoryRepository;
import com.example.ecommercebe.repository.ProductRepository;
import com.example.ecommercebe.request.CreateProductRequest;
import com.example.ecommercebe.service.user.UserService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest req) {

        Category topLevel = categoryRepository
                .findByName(req.getTopLevelCategory());
        if (topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository
                .findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());

        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(req.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository
                .findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(req.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }

        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPercent(req.getDiscountPercent());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreateAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {

        Product product = findProductById(productId);

        if (req.getQuantity() != 0) {
            product.setQuantity(req.getQuantity());
        }

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(long id) throws ProductException {

        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new ProductException("Product not found with id -" + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> size,
                                       Integer minPrice, Integer maxPrice, Integer minDiscount,
                                       String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }

        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

        return filteredProducts;
    }

    @Override
    public List<Product> importProductData(MultipartFile file) throws ProductException {
        try (InputStream inputStream = file.getInputStream()) {
            // Log that the method is entered
            System.out.println("Received a request to import product data.");

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet contains the data

            List<Product> importedProducts = new ArrayList<>();

            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Assuming the columns in Excel file are in a specific order
                String imageUrl = getStringValue(row.getCell(0));
                String brand = getStringValue(row.getCell(1));
                String title = getStringValue(row.getCell(2));
                String color = getStringValue(row.getCell(3));
                Long discountedPrice = getNumericValue(row.getCell(4));
                Long price = getNumericValue(row.getCell(5));
                int discountPercent = getNumericValue(row.getCell(6)).intValue();
                String sizesStr = getStringValue(row.getCell(7));
                int quantity = getNumericValue(row.getCell(8)).intValue();
                String topLevelCategory = getStringValue(row.getCell(9));
                String secondLevelCategory = getStringValue(row.getCell(10));
                String thirdLevelCategory = getStringValue(row.getCell(11));
                String description = getStringValue(row.getCell(12));

                // Find or create category entities
                Category topLevel = categoryRepository.findByName(topLevelCategory);
                if (topLevel == null) {
                    Category topLevelCategoryEntity = new Category();
                    topLevelCategoryEntity.setName(topLevelCategory);
                    topLevelCategoryEntity.setLevel(1);
                    topLevel = categoryRepository.save(topLevelCategoryEntity);
                }

                Category secondLevel = categoryRepository.findByNameAndParent(secondLevelCategory, topLevel.getName());
                if (secondLevel == null) {
                    Category secondLevelCategoryEntity = new Category();
                    secondLevelCategoryEntity.setName(secondLevelCategory);
                    secondLevelCategoryEntity.setParentCategory(topLevel);
                    secondLevelCategoryEntity.setLevel(2);
                    secondLevel = categoryRepository.save(secondLevelCategoryEntity);
                }

                Category thirdLevel = categoryRepository.findByNameAndParent(thirdLevelCategory, secondLevel.getName());
                if (thirdLevel == null) {
                    Category thirdLevelCategoryEntity = new Category();
                    thirdLevelCategoryEntity.setName(thirdLevelCategory);
                    thirdLevelCategoryEntity.setParentCategory(secondLevel);
                    thirdLevelCategoryEntity.setLevel(3);
                    thirdLevel = categoryRepository.save(thirdLevelCategoryEntity);
                }

                Set<Size> sizes = parseSizes(sizesStr);

                // Create a Product object and set category
                Product product = new Product();
                product.setTitle(title);
                product.setColor(color);
                product.setDescription(description);
                product.setDiscountedPrice(discountedPrice);
                product.setDiscountPercent(discountPercent);
                product.setImageUrl(imageUrl);
                product.setBrand(brand);
                product.setPrice(price);
                product.setSizes(sizes);
                product.setQuantity(quantity);
                product.setCategory(thirdLevel);
                product.setCreateAt(LocalDateTime.now());

                productRepository.save(product);

                importedProducts.add(product);

            }

            // Log that processing is complete
            System.out.println("Product data import completed successfully.");

            return importedProducts;
        } catch (IOException e) {
            // Log the exception if an error occurs
            System.err.println("Failed to import product data from Excel: " + e.getMessage());
            throw new ProductException("Failed to import product data from Excel: " + e.getMessage());
        }
    }

    @Override
    public List<Product> findProductVariations(Long productId) {
        return productRepository.findByParentProductId(productId);
    }

    @Override
    public Product findProductVariationById(Long productId, Long variationId) throws ProductException {
        Optional<Product> productOptional = productRepository.findByParentProductIdAndId(productId, variationId);

        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductException("Product variation not found");
        }
    }

    @Override
    public Product createProductVariation(Long productId, Long variationId) throws ProductException {

        Product parentProduct = findProductById(productId);

        Product variationProduct = new Product();
        variationProduct.setParentProduct(parentProduct);

        return productRepository.save(variationProduct);
    }

    @Override
    public void deleteProductVariation(Long productId, Long variationId) throws ProductException {
        Product parentProduct = findProductById(productId);

        Product variationProduct = findProductById(variationId);

        if (variationProduct.getParentProduct() != parentProduct) {
            throw new ProductException("Variation product does not belong to the specified parent product.");
        }

        parentProduct.getVariations().remove(variationProduct);

        productRepository.delete(variationProduct);
    }

    @Override
    public Product updateProductVariation(Long productId, long variationId, Product req) throws ProductException {
        Product parentProduct = findProductById(productId);

        Product variationProduct = findProductById(variationId);

        if (variationProduct.getParentProduct() != parentProduct) {
            throw new ProductException("Variation product does not belong to the specified parent product.");
        }

        if (req.getQuantity() != 0) {
            variationProduct.setQuantity(req.getQuantity());
        }

        return productRepository.save(variationProduct);
    }

    private String getStringValue(Cell cell) {
        return cell == null ? null : cell.getStringCellValue();
    }

    private Long getNumericValue(Cell cell) {
        return cell == null ? null : (long) cell.getNumericCellValue();
    }

    // Helper method to parse size information and create Size objects
    private Set<Size> parseSizes(String sizesStr) {
        Set<Size> sizes = new HashSet<>();
        if (sizesStr != null && !sizesStr.isEmpty()) {
            String[] sizeArray = sizesStr.split(",");
            for (String sizeStr : sizeArray) {
                String[] parts = sizeStr.trim().split(":");
                if (parts.length == 2) {
                    String sizeName = parts[0].trim();
                    int sizeQuantity = Integer.parseInt(parts[1].trim());

                    // Create a Size object and add it to the set
                    Size size = new Size();
                    size.setName(sizeName);
                    size.setQuantity(sizeQuantity);
                    sizes.add(size);
                }
            }
        }
        return sizes;
    }
}
