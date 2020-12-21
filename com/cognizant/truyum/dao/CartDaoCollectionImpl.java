package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao{
	
	//instance variable
	private static HashMap<Long, Cart> userCarts;
	
	//constructor
	public CartDaoCollectionImpl() {
		super();
		if(userCarts==null) {
			HashMap<Long, Cart> cartItems=new HashMap<>();
			setUserCarts(cartItems);
		}
	}
	
	//Getters and Setters
	public static HashMap<Long, Cart> getUserCarts() {
		return userCarts;
	}
	public static void setUserCarts(HashMap<Long, Cart> userCarts) {
		CartDaoCollectionImpl.userCarts = userCarts;
	}

	//To get the menuitem corresponding to the specified menuItemId
	//To check for existence of user based on userId
	//Add the new menuitem into the cart
	public void addCartItem(long userId,long  menuItemId) {
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl=new MenuItemDaoCollectionImpl();
		MenuItem item=menuItemDaoCollectionImpl.getMenuItem(menuItemId);
		if(userCarts.containsKey(userId)) {
			Cart cart3=userCarts.get(userId);
			List<MenuItem> list=new ArrayList<>();
			float price=0.0f;
			list=cart3.getMenuItemList();
			price=(float) cart3.getTotal();
			list.add(item);
			price=price+item.getPrice();
			Cart updatedCart1=new Cart(list,price);
			userCarts.put(userId, updatedCart1);
		}
		else {
			List<MenuItem> newList=new ArrayList<>();
			newList.add(item);
			Cart cartNew=new Cart(newList,item.getPrice());
			userCarts.put(userId, cartNew);
			}
	}
	
	//To get list of menu items added by a customer to cart
	public List<MenuItem> getAllCartItems(long userId)throws CartEmptyException{
		List<MenuItem> menuItemList=new ArrayList<>();
			Cart cart=userCarts.get(userId);
			menuItemList=cart.getMenuItemList();
			if(menuItemList==null) {
				throw new CartEmptyException("Cart is Empty!!!");
			}
			else {
			return menuItemList;
			}
	}
	
	//To remove a menu item from the cart
	public void removeCartItem(long userId, long menuItemId) {
		Cart cart=userCarts.get(userId);
		List<MenuItem> list=cart.getMenuItemList();
		float tprice=(float) cart.getTotal();
		for(MenuItem mi:list) {
			if(mi.getId()==menuItemId) {
				tprice=tprice-mi.getPrice();
				list.remove(mi);
				break;
			}
		}
	}
}
