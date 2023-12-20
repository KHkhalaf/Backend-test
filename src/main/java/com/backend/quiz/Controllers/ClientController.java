package com.backend.quiz.Controllers;

import com.backend.quiz.Exceptions.ResourceNotFoundException;
import com.backend.quiz.models.Client;
import com.backend.quiz.services.ClientService;
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
 * This is the controller to get, create, modify client information
 *
 * @author khalil khalaf
 */

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    public ClientService clientService;

    public ClientController (){ }

    /**
     * get clients
     *
     * @param model : view model
     * @return list of clients
     */
    @RequestMapping("/all")
    public String viewClients(Model model){
        List<Client> clients = clientService.listAll();

        model.addAttribute("clients", clients);

        return "ClientViews/List_Clients";
    }

    /**
     * get create client's view
     *
     * @param model : view model
     * @return view with client model
     */
    @RequestMapping("/create")
    public String addClient(Model model) {
        Client client = new Client();
        model.addAttribute("client",client);

        return "ClientViews/Create";
    }
    /**
     * save new client
     *
     * @param client : client's model comes from the submitted form
     * @return redirected URL to clients list view
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client") Client client) {

        clientService.save(client);

        return "redirect:/client/all";
    }
    /**
     * get edit client's view
     *
     * @param id : client's id comes from the selected client
     * @return ModelAndView and the client by id
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView edit( @PathVariable(name = "id")int id) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("ClientViews/edit");
        Client client = clientService.get(id);
        mav.addObject("client", client);

        return mav;
    }
    /**
     * update a client
     *
     * @param client : client's model comes from the submitted form
     * @return redirected URL to clients list view
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String edit(@ModelAttribute("client") Client client) throws ResourceNotFoundException {

        clientService.editClient(client);

        return "redirect:/client/all";
    }

}
