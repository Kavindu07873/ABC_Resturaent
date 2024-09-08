import React from "react";
import {BsTrash} from "react-icons/bs";
import {useDispatch} from "react-redux";
import {LinkContainer} from "react-router-bootstrap";
import {Col, Row} from "reactstrap";
import {
  removeFromCart,
  updateCartItemQty,
} from "../../../../features/cart/cartServices";

const CartItemCard = ({item: {color, totalPrice, product, quantity, size, id}}) => {
  const dispatch = useDispatch();
  size =product?.description;
  console.log("CartItemCard   : ",totalPrice)
  return (
    <div style={{position: "relative"}}>
      {/* Remove Icon */}
      <div
        style={{
          position: "absolute",
          zIndex: 2,
          top: "0",
          right: "-11px",
          background: "rgba(0, 0 , 0, 0.7)",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          padding: "5px",
        }}
        onClick={() => dispatch(removeFromCart(id))}
      >
        <BsTrash size={20} color={"white"} />
      </div>

      <Row
        xs={5}
        className="cart-item mb-3 py-3 bg-light rounded align-items-center"
        style={{position: "relative"}}
      >
        {/* Image */}
        <Col className="text-center">
          <img
            src={product?.image}
            alt="product-img"
            style={{objectFit: "contain", maxWidth: "100%", maxHeight: "90px"}}
          />
        </Col>

        {/* Name */}
        <Col className="text-center">
          <LinkContainer
            to={`/products/${product?.id}`}
            style={{cursor: "pointer"}}
          >
            <h6>{product?.name}</h6>
          </LinkContainer>
        </Col>

        <Col className="text-center">
          <div className="d-flex align-items-center justify-content-around flex-column gap-2">
            {/* Color */}
            {/* Size */}
            {size && (
              <div
              >
                {size}
              </div>
            )}
          </div>
        </Col>
        <Col className="text-center">
          {product && quantity && (
            <select
              id="qtySelect"
              name="select"
              type="select"
              style={{padding: "10px 10px"}}
              value={quantity}
              onChange={(e) =>
                dispatch(
                  updateCartItemQty({cartItemId: id, quantity: e.target.value})
                )
              }
            >
              {[...Array(product?.quantityInStock)?.keys()].map((i) => (
                <option key={i + 1} value={i + 1}>
                  {i + 1}
                </option>
              ))}
            </select>
          )}
        </Col>
        <Col className="text-center">
          {totalPrice && <span style={{color: "red"}}>Rs. {totalPrice}</span>}
        </Col>
      </Row>
    </div>
  );
};

export default CartItemCard;
