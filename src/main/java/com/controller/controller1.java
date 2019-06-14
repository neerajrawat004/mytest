package com.controller;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.mongodb.MongoDbConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.request.FormDataReqest;

@Controller
public class controller1 {
	@RequestMapping("/")
	public ModelAndView homepage(Model model) {
		FormDataReqest fmd = new FormDataReqest();
		model.addAttribute("formdata", fmd);
		ModelAndView mav = new ModelAndView("homepage");
		return mav;
	}

	@RequestMapping(value = "formsubmit", method = RequestMethod.POST)
	public @ResponseBody String[] method2(@ModelAttribute("formdata") FormDataReqest fmd) {
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo("localhost", 27017);
		DB db= mongo.getDB("testDB");
		DBCollection coll= db.getCollection("testDB");
		BasicDBObject document = new BasicDBObject();
		document.put("name",fmd.getName());
		document.put("email",fmd.getEmail());
		coll.insert(document);
		DBCursor hm= coll.find();
		HashSet<String> key= new HashSet<String>();
		while (hm.hasNext()) {
		}
		return new String[] {key.toString()};
		}
		
		
		
		
		

	}
