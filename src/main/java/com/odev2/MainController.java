package com.odev2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping("/")
@EnableCaching
public class MainController {

    @Autowired
    Helper helper;




    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView addBook(HttpSession session, HttpServletRequest request){
        ModelAndView modelAndView= new ModelAndView("addbook");



        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addBookWorker(HttpServletRequest request, HttpServletResponse response, HttpSession session){

        String bookName =request.getParameter("name");
        String author =request.getParameter("name");
        String isbn = request.getParameter("name");


if(bookName!=null && author!=null && isbn!=null && isNumeric(request.getParameter("price"))) {

    float price = Float.parseFloat(request.getParameter("price"));
    Model model = new Model( bookName, author, isbn, price);

    helper.addBook(model);


    return "redirect:/";
}
        else return "error";
    }

    public static boolean isNumeric(String s) {
       try {
           float f=Float.parseFloat(s);
       }
       catch (Exception ex){
 return false;
       }

        return true;
    }

    @RequestMapping(value = "listbook",method = RequestMethod.GET)
    public ModelAndView showBooks(){

        ModelAndView modelAndView=new ModelAndView("listbook");

        List<Model> models = helper.getBooks();

         modelAndView.addObject("list", models);

        System.out.println(models.size());

        return modelAndView;

    }




}
