package com.cognizant.truyum.dao;

import java.util.List;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;;
public class MenuItemDaoCollectionImplTest {

	public static void main(String args[]) 	//calling all methods in main()
	{
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
	}
	
	//Test the implementation of getMenuItemListAdmin() method
	public static void testGetMenuItemListAdmin() {
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl=new MenuItemDaoCollectionImpl();
		List<MenuItem> menuListAdmin= menuItemDaoCollectionImpl.getMenuItemListAdmin();
		System.out.println("MENU ITEM LIST FOR ADMIN!!!");
		for(int i=0;i<menuListAdmin.size();i++) {
			System.out.println(menuListAdmin.get(i));
		}
		System.out.println();
	}
	
	//Test the implementation of getMenuItemListCustomer() method
	public static void testGetMenuItemListCustomer() {
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl2=new MenuItemDaoCollectionImpl();
		List<MenuItem> menuItemListCustomer=menuItemDaoCollectionImpl2.getMenuItemListCustomer();
		System.out.println("MENU ITEM LIST FOR CUSTOMER!!!");
				for(int i=0;i<menuItemListCustomer.size();i++) {
					System.out.println(menuItemListCustomer.get(i));
				}
				System.out.println();
	}
	
	//Test the implementation of modifyMenuItem() method to check if the menu item details are modified
	public static void testModifyMenuItem() {
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl3=new MenuItemDaoCollectionImpl();
		System.out.println("MODIFY MENU ITEM!!!");
		System.out.println("Before Modification:");
		MenuItem item=menuItemDaoCollectionImpl3.getMenuItem(3);
		System.out.println(item);
		System.out.println();
		MenuItem menuItem = new MenuItem(3, "Pizza", (float)139.00, true, DateUtil.convertToDate("21/08/2018"), "Main Course", true);
		menuItemDaoCollectionImpl3.modifyMenuItem(menuItem);
		MenuItem item1=menuItemDaoCollectionImpl3.getMenuItem(3);
		System.out.println("After Modification:");
		System.out.println(item1);
		System.out.println();
	}
	
	public static void testGetMenuItem() {}
}
