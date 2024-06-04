package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MyController {

	 @Autowired
     private EmailService emailService;
	 
	 private final ComputerJDBCTemp computerJDBCTemp;
	 
	 private final pcOrdJDBCTemp pcOrdJDBCTemp;
	    @Autowired
	    public MyController(ComputerJDBCTemp computerJDBCTemp, pcOrdJDBCTemp pcOrdJDBCTemp) {
	        this.computerJDBCTemp = computerJDBCTemp;
	        this.pcOrdJDBCTemp = pcOrdJDBCTemp;
	    }
	    @GetMapping("/")
	    public String mostraForm(Model model) {
	        model.addAttribute("computer", new Computer());
	        return "formComputer";
	    }
	    @PostMapping("/creaComputer")
	    public String creaComputer(@RequestParam("marca") String marca,
	                               @RequestParam("tipologia") String tipologia,
	                               @RequestParam("modello") String modello,
	                               @RequestParam("descrizione") String descrizione,
	                               @RequestParam("qnt") int qnt,
	                               @RequestParam("urlImage") String urlImage,
	                               @RequestParam("prezzo") double prezzo,
	                               Model model) {
	    	
	    	String [] ImgUrl = urlImage.split("\"");
			
			String urlImg = ImgUrl[3];
	    	
	        Computer computer = new Computer();
	        computer.setMarca(marca);
	        computer.setTipologia(tipologia);
	        computer.setModello(modello);
	        computer.setDescrizione(descrizione);
	        computer.setQnt(qnt);
	        computer.setUrlImage(urlImg);
	        computer.setPrezzo(prezzo);

	        model.addAttribute("computer", computer);

			computerJDBCTemp.insertComputer(marca, tipologia, modello, descrizione, qnt, urlImg, prezzo);

	        return "mostraComputer";
	    }
		 @GetMapping("/magazzinoComputer")
		 public String getComputer(Model model){
			
			 ArrayList <Computer> lista = computerJDBCTemp.ritornaComputer();
			 model.addAttribute("lista", lista);
			 
			 return "magazzinoComputer";
			 
		 }
		 @GetMapping("/change")
		 public String change(Model model) {
			 
			 ArrayList <Computer> lista = computerJDBCTemp.ritornaComputer();
			 model.addAttribute("lista", lista);
			 return "change";
		 }
		 @PostMapping("/change")
		 public ResponseEntity<String> change(@RequestParam("pc") String [] ordini,@RequestParam("pezzi") String [] pezzi,Model model){
	 
			 ArrayList<Integer> pc = new ArrayList<>();
			 ArrayList<Integer> qnt = new ArrayList <>();
			 
	 
			 for (String s: pezzi) {
				 if (!s.isEmpty()) {
				int x = Integer.parseInt(s);
				qnt.add(x);
			 }}
			 for (String s: ordini) {
				 if (!s.isEmpty()) {
				int x = Integer.parseInt(s);
				pc.add(x);
			 }}

			for (int i = 0; i < qnt.size(); i++) {
			computerJDBCTemp.updatePezzi(qnt.get(i), pc.get(i));	
			}
			return ResponseEntity.ok("Change successfully");
		 }
		 @GetMapping("/acquista")
		 public String acquista(Model model) {
			 
			 ArrayList <Computer> lista = computerJDBCTemp.ritornaComputer();
			 model.addAttribute("lista", lista);
			 return "acquista";
		 }
		 @PostMapping("/buyPc")
		 public String addWatch(@RequestParam("ordini") String [] ordini,@RequestParam("pezzi") String [] pezzi,Model model){
			 
			 ArrayList <Computer> lista = computerJDBCTemp.ritornaComputer();
			 ArrayList<Integer> qnt = new ArrayList <>();
			 ArrayList<Prenotazioni> pc = new ArrayList <>();
			 
			 for (String s: pezzi) {
				 if (!s.isEmpty()) {
				int x = Integer.parseInt(s);
				qnt.add(x);
				
			 }}
			 double prezzo = 0;
			 
			
				 
				 for (int j = 0; j < ordini.length; j++) {
					 for (int i = 0; i < lista.size(); i++) {
					 if (lista.get(i).id == (Integer.parseInt(ordini[j]))) {
						
						 computerJDBCTemp.updatePezzi2(qnt.get(j),lista.get(i).id  );
						 prezzo += lista.get(i).getPrezzo() * qnt.get(j);
						 Prenotazioni computer = new Prenotazioni();
						 computer.setModello(lista.get(i).getModello());
						 computer.setQnt(qnt.get(j));
						 pc.add(computer);
						 
					 }
					 
				 }
			 }
			
			
			System.out.println(prezzo);
			
	   model.addAttribute("lista", pc);
	  
	   model.addAttribute("prezzo", prezzo);
	   	 
			 return "conferma";
		 }
			@GetMapping("/formEmail")
			public String formEmail(){
				
				return "formEmail";
			}
			
			
		    @PostMapping("sendEmail")
		    public ResponseEntity<String> sendEmail(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("text") String text) {
		        emailService.sendSimpleEmail(to, subject, text);
		        return ResponseEntity.ok("Email sent successfully");
		    }
		    @PostMapping("confPc")
		    public ResponseEntity<String> confermaPc(@RequestParam("modello") String [] ordini,
		    										 @RequestParam("qnt") String [] pezzi,
		    										 @RequestParam("prezzo") String prezzo,
		    										 @RequestParam("email") String email,
		    										 Model model) {
		    ArrayList<Prenotazioni> pc = new ArrayList <>();
		    ArrayList<Integer> qnt = new ArrayList <>();
		    ArrayList <Computer> lista = computerJDBCTemp.ritornaComputer();
		      	 
		   		 for (String s: pezzi) {
		   			 if (!s.isEmpty()) {
		   			int x = Integer.parseInt(s);
		   			qnt.add(x);
		   			
		   		 }}
		   		 
		   		 
		   		
		   			 
		   			 for (int j = 0; j < ordini.length; j++) {
		   				 for (int i = 0; i < lista.size(); i++) {
		   				 if (lista.get(i).getModello().equals(ordini[j])) {
		   					
		   					 
		   					
		   					 Prenotazioni computer = new Prenotazioni();
		   					 computer.setModello(lista.get(i).getModello());
		   					 computer.setQnt(qnt.get(j));
		   					 pc.add(computer);
		   					 
		   				 }
		   				 
		   			 }}
		   			 for (Prenotazioni computer: pc) {
		   			 pcOrdJDBCTemp.updatePezzi(computer.getQnt(), computer.getModello());}
		   			 String to = email;
		   			 String subject = "ordine da talentformStore confermato";
		   			 String text = "";
		   			 text += "Hai acquistato: \n";
		   			 for (Prenotazioni computer : pc) {
		   				 text += "Modello: " + computer.getModello() + "\n";
		   				 text += "Numero pezzi:" + computer.getQnt() + "\n";
		   			 }
		   			 text += "Il prezzo totale da pagare è: " + prezzo + " €";
		   			 emailService.sendSimpleEmail(to, subject, text);
		   			 
		   			 return ResponseEntity.ok("Email sent succesfully, grazie per l'acquisto!");
}
		    @GetMapping("/showPcOrders")
		    public String showPcOrders(Model model) {
		        ArrayList<Prenotazioni> pcOrders = new ArrayList<>();
		        pcOrders = pcOrdJDBCTemp.ritornaOrdPc();
		        
		        // Convertiamo la lista in una stringa JSON
		        ObjectMapper objectMapper = new ObjectMapper();
		        String pcOrdersJson = "";
		    	try {
					pcOrdersJson = objectMapper.writeValueAsString(pcOrders);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       

		        // Passiamo la stringa JSON al modello
		        model.addAttribute("pcOrdersJson", pcOrdersJson);
		        
		        model.addAttribute("pcOrders", pcOrders);
		        return "tabellaordini";
		    }
}
