package com.t1908e.examspringwebserivceclient.controller;

import com.t1908e.examspringwebserivceclient.service.Product;
import com.t1908e.examspringwebserivceclient.service.ProductService;
import com.t1908e.examspringwebserivceclient.service.ProductServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        ProductServiceService productServiceService = new ProductServiceService();
        ProductService service = productServiceService.getProductServicePort();
        List<Product> allProducts = service.getAllProducts();
        model.addAttribute("list", allProducts);
        return "products/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String save(Model model, @ModelAttribute("product") Product product) {
        ProductServiceService productServiceService = new ProductServiceService();
        ProductService service = productServiceService.getProductServicePort();
        service.addProduct(product);
        return "redirect:/products/index";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/sell")
    public String sell(Model model, @RequestParam(value="productId") int productId,  @RequestParam(value="qty") int qty ) {
        ProductServiceService productServiceService = new ProductServiceService();
        ProductService service = productServiceService.getProductServicePort();
        service.sellProduct(productId, qty);
        return "redirect:/products/index";
    }
}
