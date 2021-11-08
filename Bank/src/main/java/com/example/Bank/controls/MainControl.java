package com.example.Bank.controls;

import com.example.Bank.domain.Clients;
import com.example.Bank.domain.Credits;
import com.example.Bank.domain.Ras;
import com.example.Bank.domain.ofCredits;
import com.example.Bank.repos.ClientsRepo;
import com.example.Bank.repos.CreditsRepo;

import com.example.Bank.repos.RasRepo;
import com.example.Bank.repos.ofCreditsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
public class MainControl {
    private String regEdit = "";

    @Autowired
    private ClientsRepo clientsRepo;
    @Autowired
    private CreditsRepo creditsRepo;
    @Autowired
    private ofCreditsRepo ofCreditsRepo;
    @Autowired
    private RasRepo rasRepo;

    @GetMapping(value="/")
    public String main(Map<String,Object> map){
        map.put("main","Main Window");
        return "main";
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value="/clients")
    public String clients(Map<String,Object> map){
        mapAdd(map);
        map.put("res", "");
        mapFindClients(map);
        regEdit = "";
        return "clients";
    }

    private void mapAdd(Map<String,Object> map, Clients clients){
        map.put("nameG","Clients");

        map.put("name", clients.getName());
        map.put("surname", clients.getSurname());
        map.put("otchestvo", clients.getOtchestvo());
        map.put("phoneNumber", String.valueOf(clients.getPhoneNumber()));
        map.put("mail", clients.getMail());
        map.put("passportNumber", String.valueOf(clients.getPassportNumber()));
        map.put("nameBut", "Редактировать");
    }

    private void mapAdd(Map<String,Object> map){
        map.put("nameG","Clients");

        map.put("name", "");
        map.put("surname", "");
        map.put("otchestvo", "");
        map.put("phoneNumber", "");
        map.put("mail", "");
        map.put("passportNumber", "");
        map.put("nameBut", "Добавить");
    }

    private void  mapFindClients(Map<String,Object> map){
        Iterable<Clients> it = clientsRepo.findAll();
        map.put("clients", it);
    }


    @PostMapping(value="/clients")
    public String clientsAdd(@RequestParam String name, @RequestParam String surname,
                            @RequestParam String otchestvo, @RequestParam Integer phoneNumber,
                            @RequestParam String mail, @RequestParam Integer passportNumber,
                            Map<String,Object> map){

        Clients clients = new Clients(name, surname, otchestvo, mail, phoneNumber, passportNumber);

        if (provClient(clients))
            map.put("res", "Создан");
        else
            map.put("res", "Такой клиент уже есть");

        mapAdd(map);
        mapFindClients(map);


        return "clients";
    }

    private Boolean provClient(Clients clients){
        Iterable<Clients> it = clientsRepo.findAll();

        boolean pr = true;

        for(Clients cl: it)
            if(cl.getName().equals(clients.getName()) && cl.getOtchestvo().equals(clients.getOtchestvo()) &&
                    cl.getSurname().equals(clients.getSurname()) && cl.getPassportNumber() == clients.getPassportNumber()){
                pr =false;
                break;
            }

        if(pr)
            clientsRepo.save(clients);

        return pr;
    }

    @GetMapping("/delete")
    public String clientsDelete(@RequestParam String id,
                                Map<String,Object> map){
         clientsRepo.delete(clientsRepo.findById(UUID.fromString(id)));

        mapFindClients(map);
        map.put("res", "");
        mapAdd(map);
        return "clients";
    }

    @GetMapping("/redact")
    public String clientsEdit(@RequestParam String id,
                                Map<String,Object> map){
        mapAdd(map, clientsRepo.findById(UUID.fromString(id)));

        mapFindClients(map);
        map.put("res", "");
        regEdit = id;

        return "clients";
    }

    @PostMapping(value="/redact")
    public String clientEdit2(@RequestParam String name, @RequestParam String surname,
                              @RequestParam String otchestvo, @RequestParam Integer phoneNumber,
                              @RequestParam String mail, @RequestParam Integer passportNumber,
                              Map<String,Object> map){

        if(!regEdit.equals("")) {
            Clients clients = clientsRepo.findById(UUID.fromString(regEdit));
            clients.setName(name);
            clients.setSurname(surname);
            clients.setOtchestvo(otchestvo);
            clients.setPhoneNumber(phoneNumber);
            clients.setMail(mail);
            clients.setPassportNumber(passportNumber);
            clientsRepo.save(clients);
            regEdit = "";
        }

        mapAdd(map);
        mapFindClients(map);
        map.put("res", "");
        return "clients";
    }




/////////////////////////////////////////////////////////////////////////////////////
    private void mapFindCredits(Map<String,Object> map){
        Iterable<Credits> it = creditsRepo.findAll();
        map.put("credits", it);
    }

    private void mapAddCr(Map<String,Object> map){
        map.put("nameBut", "Добавить");
        map.put("limitMin", "");
        map.put("limitMax", "");
        map.put("proc", "");
    }

    private void mapEditCr(Map<String,Object> map, Credits credits){
        map.put("nameBut", "Редактировать");
        map.put("limitMin", credits.getLimitMin());
        map.put("limitMax", credits.getLimitMax());
        map.put("proc", credits.getProc());
    }

    @GetMapping(value="/credits")
    public String credits(Map<String,Object> map){
        mapFindCredits(map);
        mapAddCr(map);
        map.put("rez", "");
        regEdit = "";
        return "credits";
    }

    @PostMapping(value = "/credits")
    public String creditsAdd(@RequestParam double limitMin, @RequestParam double limitMax, @RequestParam double proc, Map<String,Object>map){
        if(limitMax > limitMin) {
            Credits credits = new Credits(limitMin, limitMax, proc);
            creditsRepo.save(credits);
            map.put("rez", "");
        }
        else
            map.put("rez", "Не правльные границы лимита");

        mapFindCredits(map);
        mapAddCr(map);
        return "credits";
    }

    @GetMapping("/crDelete")
    public String creditsDelete(@RequestParam String id,
                                Map<String,Object> map){
        creditsRepo.delete(creditsRepo.findById(UUID.fromString(id)));

        mapFindCredits(map);
        mapAddCr(map);
        map.put("rez", "");

        return "credits";
    }

    @GetMapping("/crRedact")
    public String creditsEdit(@RequestParam String id,
                              Map<String,Object> map){
        mapEditCr(map, creditsRepo.findById(UUID.fromString(id)));

        regEdit = id;
        mapFindCredits(map);
        map.put("rez", "");
        return "credits";
    }

    @PostMapping(value="/crRedact")
    public String creditsEdit2(@RequestParam double limitMin, @RequestParam double limitMax, @RequestParam double proc, Map<String,Object>map){

        if(!regEdit.equals("")) {
            Credits credits = creditsRepo.findById(UUID.fromString(regEdit));
            credits.setLimitMin(limitMin);
            credits.setLimitMax(limitMax);
            credits.setProc(proc);
            creditsRepo.save(credits);

            regEdit = "";
        }

        mapAddCr(map);
        mapFindCredits(map);
        map.put("rez", "");
        return "credits";
    }

    /////////////////////////////////////////////////////////////
    @GetMapping(value="/bank")
    public String bank(Map<String,Object> map){
        mapFindCredits(map);
        mapFindClients(map);

        return "bank";
    }
    ////////////////////////////////////////////////////////////
    @GetMapping(value = "/ofcredits")
    public String ofcredits(Map<String,Object>map){
        mapFindCredits(map);
        mapFindClients(map);
        mapOfClCr(map);
        mapEditNull(map);

        return "ofcredits";
    }

    private void mapEditNull(Map<String,Object> map){
         map.put("summaEdit", "");
         map.put("monthEdit", "");
    }

    @PostMapping(value = "/ofcredits")
    public String grafPl(@RequestParam String client, @RequestParam String credit,@RequestParam Double summa,@RequestParam Integer month, Map<String,Object>map){
        mapFindCredits(map);
        mapFindClients(map);

        ofCredits ofCredits = new ofCredits(UUID.fromString(client), UUID.fromString(credit), summa, month);
        ofCreditsRepo.save(ofCredits);


        mapRas(ofCredits, map);
        mapOfClCr(map);
        mapEditNull(map);

        return "ofcredits";
    }

    private void mapRas(ofCredits ofCredits, Map<String,Object>map){
        for (int i = 0; i < ofCredits.getMonth(); i++)
        {
            Double summa, summaTelCr, summaPr;

            Credits credits = creditsRepo.findById(ofCredits.getId_cr());

            summa = (ofCredits.getSumma()*credits.getProc()/100+ofCredits.getSumma())/ofCredits.getMonth();
            summaTelCr = ofCredits.getSumma()/ofCredits.getMonth();
            summaPr = summa - summaTelCr;

            System.out.println(ofCredits.getSumma()  + " " + LocalDate.now().plusMonths(i) + " " +  summa + " " +  summaTelCr + " " +  summaPr + " " + ofCredits.getMonth());
            Ras ras = new Ras(ofCredits.getId(), LocalDate.now().plusMonths(i).toString(), summa, summaTelCr, summaPr);
            rasRepo.save(ras);
        }
        //System.out.println(rasRepo.findByidOfCr(ofCredits.getId()));
        map.put("grafPl", rasRepo.findByidOfCr(ofCredits.getId()));
    }

    private void mapOfClCr(Map<String,Object> map){
        Iterable<ofCredits> it = ofCreditsRepo.findAll();
        map.put("ofclcr", it);
    }

    @GetMapping("/oFdelete")
    public String ofcreditsDelete(@RequestParam String id,
                                Map<String,Object> map){

        Iterable<Ras> it = rasRepo.findByidOfCr(UUID.fromString(id));
        for (Ras r: it) {
            rasRepo.delete(r);
        }

        ofCreditsRepo.delete(ofCreditsRepo.findById(UUID.fromString(id)));

        mapFindCredits(map);
        mapFindClients(map);
        mapOfClCr(map);
        mapEditNull(map);

        return "ofcredits";
    }

    @GetMapping("/oFredact")
    public String ofcreditsRedact(@RequestParam String id,
                                  Map<String,Object> map) {

        ofCredits of = ofCreditsRepo.findById(UUID.fromString(id));

        map.put("summaEdit", of.getSumma());
        map.put("monthEdit", of.getMonth());

        regEdit = id;

        mapFindCredits(map);
        mapFindClients(map);
        mapOfClCr(map);

        return "ofcredits";
    }

    @GetMapping("/oFEdit")
    public String ofcreditsEdit(@RequestParam Double summa,@RequestParam Integer month,
                                  Map<String,Object> map) {
        if(!regEdit.equals("")){
            ofCredits of = ofCreditsRepo.findById(UUID.fromString(regEdit));
            of.setMonth(month);
            of.setSumma(summa);
            ofCreditsRepo.save(of);

            Iterable<Ras> it = rasRepo.findByidOfCr(UUID.fromString(regEdit));
            for (Ras r: it) {
                rasRepo.delete(r);
            }
            mapRas(of, map);

            regEdit = "";
        }

        mapFindCredits(map);
        mapFindClients(map);
        mapOfClCr(map);
        mapEditNull(map);

        return "ofcredits";
    }
}
