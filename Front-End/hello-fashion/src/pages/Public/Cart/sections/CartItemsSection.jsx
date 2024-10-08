import React, {useState} from "react";
import OverlayLoader from "../../../../common/components/Loaders/OverlayLoader";
import useUserCart from "../../../../common/hooks/cart/useUserCart";
import {Col, Row, Button, Alert, Input, Form} from "reactstrap";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import CartItemCard from "../../../../common/components/Cards/CartItemCard";
import {Link} from "react-router-dom";
import {applyCoupon, clearCart} from "../../../../features/cart/cartServices";
import pushNotification from "../../../../common/components/Shared/Notification";
const CartItemsSection = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const {userCart, isMutation} = useUserCart();
  //HANDLE_COUPON
  const [couponCode, setCouponCode] = useState("");
  const [productId, setProductId] = useState("");
  const handleApplyCoupon = (e) => {
    e.preventDefault();
    if (!couponCode || !productId) {
      pushNotification("Please enter a coupon code and product Id", "error");
    } else {
      dispatch(applyCoupon({productId, couponCode}));
    }
    setCouponCode("");
    setProductId("");
  };
console.log("userCart.cart?.cartItems?.length : ",userCart.cart?.l)
console.log("totalPrice : ",userCart.cart?.totalPrice)

const payment = Array.isArray(userCart.cart)
  ? userCart.cart.reduce((total, item) => {
      return item?.totalPrice > 0 ? total + item.totalPrice : total;
    }, 0)
  : 0;
  console.log("payment : ",payment)


  return (
    <>
      <OverlayLoader active={isMutation?.loading} />
      <section className="cart-items-section">
        {userCart.loading || userCart.cart.length > 0 ? (
          <>
            {/* CartHead  */}
            {userCart.cart.length > 0 && (
              <>
                <Row xs={5} className="my-4 bg-light">
                  {["Image", "Name", "description", "Qty", "Price"].map(
                    (item, idx) => (
                      <Col className="text-center py-3" key={idx}>
                        <h6 className="m-0" style={{letterSpacing: "0.5px"}}>
                          {item}
                        </h6>
                      </Col>
                    )
                  )}
                </Row>
                {/* CartItems */}
                {userCart.cart?.map((item, idx) => (
                  <CartItemCard item={item} key={idx} />
                ))}
              </>
            )}

            {/* ApplyCoupon & TotalPrice */}
            {payment > 0 && (
              <Row md={2} xs={1} className={"my-4"}>
                <Col>
                  <div className="bg-light p-3 rounded d-flex flex-column gap-3">
                    {/* TotalPrice */}
                    <p
                      style={{
                        fontFamily: "sans-serif",
                        textDecoration: userCart.cart
                          ?.totalPriceAfterCouponDiscount
                          ? "line-through"
                          : "none",
                        color: userCart.cart?.totalPriceAfterCouponDiscount
                          ? "gray"
                          : "black",
                      }}
                    >
                      Cart Subtotal: Rs. {payment}
                    </p>
                    {userCart.cart?.totalPriceAfterCouponDiscount > 0 && (
                      <p style={{color: "red", fontFamily: "sans-serif"}}>
                        Cart Subtotal After Discount: Rs.{" "}
                        {userCart.cart?.totalPriceAfterCouponDiscount}
                      </p>
                    )}

                    {/* Checkout */}
                    <Button
                      block
                      size="sm"
                      color="dark"
                      onClick={() => navigate("/checkout")}
                    >
                      Checkout
                    </Button>
                    <Button
                      block
                      size="sm"
                      color="primary"
                      onClick={() => navigate("/shop")}
                    >
                      Continuo Shopping
                    </Button>

                    {/* ClearCart */}
                    <Button
                      block
                      size="sm"
                      color="danger"
                      onClick={() => dispatch(clearCart())}
                    >
                      Clear Cart
                    </Button>
                  </div>
                </Col>
              </Row>
            )}
          </>
        ) : (
          <Alert color="info">
            Your Cart Is Empty, <Link to={"/shop"}>Continue Shopping</Link>
          </Alert>
        )}
      </section>
    </>
  );
};

export default CartItemsSection;
