package com.backend.quiz.Controllers;


import com.backend.quiz.Exceptions.ResourceNotFoundException;
import com.backend.quiz.models.Category;
import com.backend.quiz.models.Product;
import com.backend.quiz.repositories.CategoryRepository;
import com.backend.quiz.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * This is the controller to get, create, modify product information
 *
 * @author khalil khalaf
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductService productService;
    @Autowired
    public CategoryRepository categoryRepository;

    public ProductController (){ }

    /**
     * get products
     *
     * @param model : view model
     * @return list of products
     */
    @RequestMapping("/all")
    public String viewProducts(Model model){
        List<Product> products = productService.listAll();

        model.addAttribute("products", products);

        return "ProductViews/List_Products";
    }

    /**
     * get create product's view
     *
     * @param model : view model
     * @return view with product model
     */
    @RequestMapping("/create")
    public String addProduct(Model model) {
        Product product = new Product();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("product",product);
        model.addAttribute("categories", categories);

        return "ProductViews/Create";
    }

    /**
     * save new product
     *
     * @param product : product's model comes from the submitted form
     * @return redirected URL to products list view
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("product") Product product) {

        productService.save(product);

        return "redirect:/product/all";
    }

    /**
     * get edit product's view
     *
     * @param id : product's id comes from the selected client
     * @return ModelAndView and the product by id
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id")int id) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("ProductViews/edit");
        Product product = productService.get(id);
        mav.addObject("product", product);

        return mav;
    }

    /**
     * update a product
     *
     * @param product : product's model comes from the submitted form
     * @return redirected URL to products list view
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String edit(@ModelAttribute("product") Product product) throws ResourceNotFoundException {

        productService.editProduct(product);

        return "redirect:/product/all";
    }

}
