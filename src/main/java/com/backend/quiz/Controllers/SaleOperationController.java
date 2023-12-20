package com.backend.quiz.Controllers;

import com.backend.quiz.Exceptions.ResourceNotFoundException;
import com.backend.quiz.models.Client;
import com.backend.quiz.models.Product;
import com.backend.quiz.models.SaleOperation;
import com.backend.quiz.models.Seller;
import com.backend.quiz.services.ClientService;
import com.backend.quiz.services.ProductService;
import com.backend.quiz.services.SaleOperationService;
import com.backend.quiz.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * This is the controller to get, create, modify SaleOperation information
 *
 * @author khalil khalaf
 */
@Controller
@RequestMapping("/SaleOperation")
public class SaleOperationController {

    @Autowired
    public SaleOperationService saleOperationService;
    @Autowired
    public SellerService sellerService;
    @Autowired
    public ClientService ClientService;
    @Autowired
    public ProductService productService;

    public SaleOperationController(){ }
    /**
     * get SaleOperation
     *
     * @param model : view model
     * @return list of SaleOperation
     */
    @RequestMapping("/all")
    public String viewSaleOperation(Model model){
        List<SaleOperation> saleOperations = saleOperationService.listAll();

        model.addAttribute("SaleOperation", saleOperations);

        return "SaleOperationViews/List_SaleOperation";
    }

    /**
     * get create SaleOperation's view
     *
     * @param model : view model
     * @return view with SaleOperation model
     */
    @RequestMapping("/create")
    public String addSaleOperation(Model model) {
        SaleOperation saleOperation = new SaleOperation();
        List<Seller> sellers = sellerService.listAll();
        List<Client> clients = ClientService.listAll();
        List<Product> products = productService.listAll();
        model.addAttribute("sellers", sellers);
        model.addAttribute("clients", clients);
        model.addAttribute("products", products);
        model.addAttribute("SaleOperation", saleOperation);

        return "SaleOperationViews/Create";
    }

    /**
     * save new SaleOperation
     *
     * @param saleOperation : SaleOperation's model comes from the submitted form
     * @return redirected URL to SaleOperation list view
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addSaleOperation(@ModelAttribute("SaleOperation") SaleOperation saleOperation) {

        saleOperationService.save(saleOperation);

        return "redirect:/SaleOperation/all";
    }

    /**
     * get edit SaleOperation's view
     *
     * @param id : SaleOperation's id comes from the selected client
     * @return ModelAndView and the SaleOperation by id
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id")int id) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("SaleOperationViews/edit");
        SaleOperation saleOperation = saleOperationService.get(id);
        mav.addObject("SaleOperation", saleOperation);

        return mav;
    }

    /**
     * update a SaleOperation
     *
     * @param saleOperationId : SaleOperation Id
     * @param quantity : the updated quantity of the SaleOperation
     * @param price : the updated price of the SaleOperation
     * @return redirected URL to SaleOperation list view
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String edit(@RequestParam("id") long saleOperationId, @RequestParam("quantity") long quantity, @RequestParam("price") long price) throws ResourceNotFoundException {

        saleOperationService.editSaleOperation(saleOperationId, quantity, price);

        return "redirect:/SaleOperation/all";
    }
}
