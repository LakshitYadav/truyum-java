package com.cognizant.truyum.dao;

import java.util.List;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {
	
	public static void main(String args[]) 	//calling all methods in main() method
	{
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testGetMenuItem();
		System.out.println("BEFORE UPDATE!!!");
		testGetMenuItemListAdmin();
		testModifyMenuItem();
		System.out.println("AFTER UPDATE!!!");
		testGetMenuItemListAdmin();
	}
	
	//To test the implementation of getMenuItemListAdmin() method
	public static void testGetMenuItemListAdmin() {
		
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		List<MenuItem> menuListAdmin= menuItemDaoSqlImpl.getMenuItemListAdmin();
		System.out.println("MENU ITEM LIST FOR ADMIN!!!");
		for(int i=0;i<menuListAdmin.size();i++) {
			System.out.println(menuListAdmin.get(i));
		}
		System.out.println();
	}
	
	//To test the implementation of getMenuItemListCustomer() method
	public static void testGetMenuItemListCustomer() {
		
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		List<MenuItem> menuListAdmin= menuItemDaoSqlImpl.getMenuItemListCustomer();
		System.out.println("MENU ITEM LIST FOR CUSTOMER!!!");
		for(int i=0;i<menuListAdmin.size();i++) {
			System.out.println(menuListAdmin.get(i));
		}
		System.out.println();
	}
	
	//To test the implementation of modifyMenuItem() method
	public static void testModifyMenuItem() {
		
		MenuItem menuItem = new MenuItem((long)3, "Pizza", (float)159.00, true, DateUtil.convertToDate("02/07/2017"), "Starters", false);
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		menuItemDaoSqlImpl.editMenuItem(menuItem);
	}
	
	//To test the implementation of getMenuItem() method
	public static void testGetMenuItem() {
		MenuItemDaoSqlImpl menuItemDaoSqlImpl=new MenuItemDaoSqlImpl();
		MenuItem menuItem=menuItemDaoSqlImpl.getMenuItem(1);
		System.out.println("GET MENU ITEM!!!");
		System.out.println(menuItem);
		System.out.println();
	}
}
