package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	public static void main(String args[]) 	//calling all methods in main()
	{
		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();
	}
	
	//Test the implementation of addCartItem() method
	//Adding of menuitems based on userId and menuItemId
	public static void testAddCartItem() {
		try {
			CartDaoCollectionImpl cartDaoCollectionImpl=new CartDaoCollectionImpl();
			cartDaoCollectionImpl.addCartItem((long)1, (long)2);
			cartDaoCollectionImpl.addCartItem((long)1, (long)5);
			cartDaoCollectionImpl.addCartItem((long)2, (long)1);
			cartDaoCollectionImpl.addCartItem((long)2, (long)4);
			System.out.println();
			System.out.println("ADD CART ITEMS!!!");
			System.out.println("Before adding new menuitem");
			List<MenuItem> cartItems;
			cartItems = cartDaoCollectionImpl.getAllCartItems(1);
			for(MenuItem mi:cartItems) {
				System.out.println(mi);
				}
			System.out.println();
			System.out.println("After adding new menuitem");
			cartDaoCollectionImpl.addCartItem((long)1, (long)1);
			
				cartItems=cartDaoCollectionImpl.getAllCartItems(1);
				for(MenuItem mi:cartItems) {
				System.out.println(mi);
				}
		} catch (CartEmptyException e) {
			System.out.println("Cart Empty!!!");
		}
		
		
	}
	
	//Test the implementation of getAllCartItems() method
	//Display the resulting list of menu items based on userId
	public static void testGetAllCartItems() {
		try {
			CartDaoCollectionImpl cartDaoCollectionImpl1=new CartDaoCollectionImpl();
			List<MenuItem> menuItemList=cartDaoCollectionImpl1.getAllCartItems(1);
			System.out.println();
			System.out.println("GET ALL CART ITEMS!!!");
			for(MenuItem mi:menuItemList) {
				System.out.println(mi);
			}
		} catch (CartEmptyException e) {
			System.out.println("Cart Empty!!!");
		}
	}
	
	//Test the implementation of removeCartItem() method
	//Remove the menu item from the list based on menuItemId
	public static void testRemoveCartItem(){
		try {
			CartDaoCollectionImpl cartDaoCollectionImpl=new CartDaoCollectionImpl();
			System.out.println();
			System.out.println("REMOVE CART ITEMS!!!");
			System.out.println("After removing the menuitem");
			cartDaoCollectionImpl.removeCartItem((long)1, (long)1);
			List<MenuItem> menuItemList=cartDaoCollectionImpl.getAllCartItems(1);
			for(MenuItem mi:menuItemList) {
				System.out.println(mi);
				}
		} catch (CartEmptyException e) {
			System.out.println("Cart Empty!!!");
		}
	}
}
