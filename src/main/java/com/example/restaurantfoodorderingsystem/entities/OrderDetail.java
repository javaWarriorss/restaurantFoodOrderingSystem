//package com.example.restaurantfoodorderingsystem.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.DataInput;
//
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//public class OrderDetail{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Integer quantity;
//    private Double productCost;
//    private Double subtotal;
//    @ManyToOne
//    @JoinColumn (name = "foodItem_id",foreignKey = @ForeignKey(name = "foodItem_id_fk"))
//    private FoodItem foodItem; // fooditem can have one or more order details
//    @ManyToOne
//    @JoinColumn (name = "order_id_fk",foreignKey = @ForeignKey(name = "order_id"))
//    private Order order; // one order can have many order details
//
//    @Override
//    public String toString() {
//        return "OrderDetail{" +
//                "id=" + id +
//                ", quantity=" + quantity +
//                ", productCost=" + productCost +
//                ", subtotal=" + subtotal +
//                ", foodItem=" + foodItem +
//                ", order=" + order +
//                '}';
//    }
//}


//            OrderDetail orderDetail = new OrderDetail();
//            //orderDetail.setId(orderDetail.getId());
//
//            orderDetail.setOrder(newOrder);
//            orderDetail.setFoodItem(foodItem);
//            orderDetail.setQuantity(cartItem.getQuantity());
//            orderDetail.setProductCost(foodItem.getPrice());  //???
//            orderDetail.setSubtotal(cartItem.getSubtotal());
//            orderDetails.add(orderDetail);


//setting product information for each cart item
//  Set<OrderDetail> orderDetails = newOrder.getOrderDetails();
////        List<OrderDetail> orderDetails = newOrder.getOrderDetails();
//  System.out.println("oorder details set: " + orderDetails.toString());


//    @OneToMany (mappedBy = "order") //  mapped in ordersDetails class by order
//    private Set<OrderDetail> orderDetails =new HashSet<>(); // order can have one or more orderdetails

//    @OneToMany (mappedBy = "order")
//    private List<OrderDetail> orderDetails =new ArrayList<>(); // order can have one or more orderdetails