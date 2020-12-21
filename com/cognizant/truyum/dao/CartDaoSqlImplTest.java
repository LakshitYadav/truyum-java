package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.List;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {
	
	public static void main(String args[]) 	//calling all methods in main() method
	{
		System.out.println("BEFORE ADDING ITEMS");
		testGetAllCartItems();
		testAddCartItem();
		System.out.println("AFTER ADDING ITEMS");
		testGetAllCartItems();
		testRemoveCartItem();
		System.out.println("AFTER REMOVING ITEMS");
		testGetAllCartItems();
	}
	
	//To test the implementatioon of addCartItem() method
	public static void testAddCartItem() {
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
//		cartDaoSqlImpl.addCartItem(1, 1);
//		cartDaoSqlImpl.addCartItem(1, 2);

		
	}
	
	//To test the implementatioon of getAllCartItems() method
	public static void testGetAllCartItems() {
		
	try {
		List<MenuItem> menuItemList=new ArrayList<>();
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
		menuItemList=cartDaoSqlImpl.getAllCartItems(1);
		for(MenuItem mi:menuItemList) {
			System.out.println(mi);
		}
		System.out.println();
	} catch (CartEmptyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	//To test the implementatioon of removeCartItem() method
	public static void testRemoveCartItem() {
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
		cartDaoSqlImpl.removeCartItem(1, 2);
	}
}
