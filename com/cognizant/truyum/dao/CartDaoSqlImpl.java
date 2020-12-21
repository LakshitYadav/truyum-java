package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao{

	//To get all the menu item list in the cart and the total price
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException{
		List<MenuItem> cartMenuItemList=new ArrayList<MenuItem>();
		Cart cart=null;
		float tprice=0.0f;
		MenuItem menuItem=null;
		try (Connection cn=ConnectionHandler.getConnection();){
			
			String sql="select * from menu_item m join cart c on m.menu_item_id=c.menu_item_id where c.user_id=?";
			PreparedStatement st=cn.prepareStatement(sql);
			st.setInt(1, (int)userId);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("menu_item_id");
				String name=rs.getString("name");
				float price=rs.getFloat("price");
				Date date=rs.getDate("date_of_launch");
				boolean active=rs.getBoolean("active");
				String category=rs.getString("category");
				boolean freeDelivery=rs.getBoolean("free_delivery");
				menuItem=new MenuItem(id, name, price, active, date, category, freeDelivery);
				cartMenuItemList.add(menuItem);				
				}
			
			
			
			String sqlprice="select sum(price) as total from cart where user_id=? group by user_id";
			PreparedStatement stmnt=cn.prepareStatement(sqlprice);
			stmnt.setInt(1, (int)userId);
			ResultSet rs1=stmnt.executeQuery();
			while(rs1.next()) {
				tprice=rs1.getFloat("total");
			}
			cart=new Cart(cartMenuItemList, tprice);
			System.out.println("Cart Items!!!");
			System.out.println();
			System.out.println("Total Price:"+tprice);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return cart.getMenuItemList();
}
	
	//To add a new menu item in the cart based on userId and menuItemId
	public void addCartItem(long userId, long menuItemId) {
		
		try(Connection cn=ConnectionHandler.getConnection();) {
			
			MenuItemDaoCollectionImpl menuItemDaoCollectionImpl=new MenuItemDaoCollectionImpl();
			MenuItem item=menuItemDaoCollectionImpl.getMenuItem(menuItemId);
			
//			String select="select * from cart where user_id=? and menu_item_id=?";
//			PreparedStatement sel=cn.prepareStatement(select);
//			sel.setInt(1,(int)userId);
//			sel.setInt(2,(int)menuItemId);
//			ResultSet rssel=sel.executeQuery();
//			if(rssel.next()) {
//				String update="update cart set quantity=quantity+1,price=price*2 where user_id=? and menu_item_id=?";
//				PreparedStatement upd=cn.prepareStatement(update);
//				upd.setInt(1,(int)userId);
//				upd.setInt(2,(int)menuItemId);
//				int rsupd=upd.executeUpdate();
//				
//				if(rsupd==1) {
//					System.out.println("QUANTITY UPDATED!!!");
//				}
//				
//			}
//			else {
			String sql="insert into cart(user_id, menu_item_id, name, price, free_delivery)values(?,?,?,?,?)";
			PreparedStatement st=cn.prepareStatement(sql);
			st.setInt(1,(int)userId);
			st.setInt(2,(int)menuItemId);
			st.setString(3,item.getName());
			st.setFloat(4,item.getPrice());
			st.setBoolean(5,item.isFreeDelivery());
			int rs=st.executeUpdate();
			if(rs>0) {
				System.out.println("Cart Updated Successfully for userId: "+userId+" menuItemId: "+menuItemId);
			}
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//To remove a menu item from the cart
	public void removeCartItem(long userId,long  menuItemId) {
		try (Connection cn=ConnectionHandler.getConnection();) {
			
			String sql="delete from cart where user_id=? and menu_item_id=?";
			PreparedStatement st=cn.prepareStatement(sql);
			st.setInt(1,(int)userId);
			st.setInt(2, (int)menuItemId);
			int rs=st.executeUpdate();
			if(rs>0) {
				System.out.println("An item is removed from the cart successfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
