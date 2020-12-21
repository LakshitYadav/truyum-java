package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao{
	
	//instance variable
	private static List<MenuItem> menuItemList;
	
	//Getters and Setters
	public static List<MenuItem> getMenuItemList() {
		return menuItemList;
	}
	public static void setMenuItemList(List<MenuItem> menuItemList) {
		MenuItemDaoCollectionImpl.menuItemList = menuItemList;
	}
	
	//constructor to initialize the menu item data that will be displayed in MenuItem listing screen of Admin
	public MenuItemDaoCollectionImpl() {
		if(menuItemList==null) {
			ArrayList<MenuItem> menuList=new ArrayList<>();
			MenuItem mi1=new MenuItem(1, "Sandwich", (float)99.00, true, DateUtil.convertToDate("15/03/2017"), "Main Course", true);
			menuList.add(mi1);
			MenuItem mi2=new MenuItem(2, "Burger", (float)129.00, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false);
			menuList.add(mi2);
			MenuItem mi3=new MenuItem(3, "Pizza", (float)149.00, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false);
			menuList.add(mi3);
			MenuItem mi4=new MenuItem(4, "French Fries", (float)57.00, false, DateUtil.convertToDate("02/07/2017"), "Starters", true);
			menuList.add(mi4);
			MenuItem mi5=new MenuItem(5, "Chocolate Brownie", (float)32.00, true, DateUtil.convertToDate("02/11/2022"), "Dessert", true);
			menuList.add(mi5);
			setMenuItemList(menuList);
			}
		}
	
	//This method returns the list of menu items for Admin
	public List<MenuItem> getMenuItemListAdmin(){
		return menuItemList;
	}
	
	//method to change the menu item data in the list of menu items
	public void modifyMenuItem(MenuItem menuItem) {
		for(int i=0;i<menuItemList.size();i++) {
			if(menuItem.equals(menuItemList.get(i))) {
				menuItemList.set(i, menuItem);
			}
		}
	}
	
	//method to retrieve a particular menu item’s detail from the menu item list
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem menuItem = null;
		for(int i=0;i<menuItemList.size();i++) {
			if(menuItemId==(menuItemList.get(i).getId())) {
				menuItem=menuItemList.get(i);
			}
		}
		return menuItem;
	}

	@Override
	//This method returns the list of menu items for Customer
	//checks if launch date of the menu item is today or before today
	//checks if  menu item available is active
	public List<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> menuListCustomer=new ArrayList<>();
		Date current=new Date();
		for(int i=0;i<menuItemList.size();i++) {
			if((menuItemList.get(i).getDateOfLaunch().before(current) || menuItemList.get(i).getDateOfLaunch().equals(current)) && 
					menuItemList.get(i).isActive()) {
				menuListCustomer.add(menuItemList.get(i));
			}
		}
		return menuListCustomer;
	}
}
