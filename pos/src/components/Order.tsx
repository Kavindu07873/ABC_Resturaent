import React, {useEffect, useState} from "react";
import Customer from "./Customer";
// import axios from "axios";
import Product from "./Product";
import AxiosInstance from '../config/axiosInstance.ts';


interface Cart {
    _id: string | undefined,
    description: string | undefined,
    unitPrice: number | '',
    qty: number | undefined,
    total: number | undefined
}

const Order: React.FC = () => {
    const styleObj: React.CSSProperties = {
        marginTop: '10px'
    }
    const bottomContext: React.CSSProperties = {
        width: '100%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between'
    }
    const totalText: React.CSSProperties = {
        color: 'red',
        margin: '0'
    }

    // find all Customer
    const [customers, setCustomers] = useState<Customer[]>([])
    // find all Products
    const [products, setProducts] = useState<Product[]>([])
    // Cart
    const [cart, setCart] = useState<Cart[]>([])

    // set Selected
    const [selectedCustomer, setSelectedCustomer] = useState<Customer | null>(null);
    const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);

    //save Customer
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [salary, setSalary] = useState<number | ''>('');


    // set Image state
    const [image, setImage] = useState<File | null>(null);
    //save Products
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [unitPrice, setUnitePrice] = useState<number | ''>('');
    const [qtyOnHand, setQtyOnHand] = useState<number | ''>('');
    // user qty
    const [userQty, setUserQty] = useState<number>(0);
    // net Total
    const [netTotal, setNetTotal] = useState<number>(0);

    useEffect(() => {
        findAllCustomers();
        findAllProducts();

    }, [])


    const setTotal = () => {
        let amount = 0;

        cart.map((data) => {
            console.log(" Total Price : "+ data.total)
            // @ts-ignore
            amount += data?.total;
            setNetTotal(amount);
        })
    }


    const findAllCustomers = async () => {
        try {
            const response = await AxiosInstance.get('/customer/find-all?searchText=&page=1&size=4');
            setCustomers(response.data);
            console.log(customers);
        } catch (e) {
            console.log(e)
        }
    }

    const findAllProducts = async () => {
        try {
            const response = await AxiosInstance.get('/products/find-all?searchText=&page=1&size=4');
            setProducts(response.data);
            console.log(products);
        } catch (e) {
            console.log(e)
        }
    }

    const getCustomerById = async (id: string) => {
        const customer = await AxiosInstance.get('/customer/find-by-id/' + id);
        console.log(customer.data)
        setSelectedCustomer(customer);
        setAddress(customer.data.address);
        setSalary(parseFloat(customer.data.salary));
    }

    const getProductById = async (id: string) => {
        const product = await AxiosInstance.get('/products/find-by-id/' + id);
        console.log(product.data)
        setSelectedProduct(product.data)
        setProductName(product.data.name)
        setDescription(product.data.description)
        setUnitePrice(product.data.unitPrice)
        setQtyOnHand(product.data.qtyOnHand)
    }

    const addToCart = async (newItem: Cart) => {
        setCart((prevState) => [...prevState, newItem]);
        setTotal();
    }

    return (
        <>
            <br/>
            <div className="container">
                <div className="row">
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="customer" style={styleObj}>Select Customer</label>
                            <select id='customer' className='form-control' onChange={(e) => {
                                console.log(e.target.value);
                                getCustomerById(e.target.value);
                            }}>
                                <option value="">Select Value</option>
                                {customers.map((customer, index) => (
                                    <option key={index} value={customer._id}>{customer.name}</option>
                                ))}
                            </select>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-4" style={styleObj}>
                        <div className="form-group">
                            <label htmlFor="price">Customer Address</label>
                            <input value={address} type="text" disabled className='form-control' id='address'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-4" style={styleObj}>
                        <div className="form-group">
                            <label htmlFor="qty">Customer Salary</label>
                            <input value={salary} type="number" disabled className='form-control' id='salary'/>
                        </div>
                    </div>
                </div>
                <hr/>
                <div className="row">
                    <div className="col-12 col-sm-6 col-md-3">
                        <div className="form-group">
                            <label htmlFor="products" style={styleObj}>Select Product</label>
                            <select id='products' className='form-control' onChange={(e) => {
                                console.log(e.target.value);
                                getProductById(e.target.value);
                            }}>
                                <option value="">Select Value</option>
                                {products.map((product, index) => (
                                    <option key={index} value={product._id}>{product.name}</option>
                                ))}
                            </select>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-3" style={styleObj}>
                        <div className="form-group">
                            <label htmlFor="description">Product Description</label>
                            <input value={description} type="text" disabled className='form-control' id='description'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-2" style={styleObj}>
                        <div className="form-group">
                            <label htmlFor="price">Unit Price</label>
                            <input value={unitPrice} type="number" disabled className='form-control' id='price'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-2" style={styleObj}>
                        <div className="form-group">
                            <label htmlFor="qtyOnHand">Qty On Hand</label>
                            <input value={qtyOnHand} type="number" disabled className='form-control' id='qtyOnHand'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-2" style={styleObj}>
                        <div className="form-group">
                            <label htmlFor="qty">Qty</label>
                            <input onChange={(e) => {
                                setUserQty(parseFloat(e.target.value))
                            }} type="number" className='form-control' id='qty'/>
                        </div>
                    </div>
                </div>
                <hr/>
                <div className="row">
                    <div className="col-12">
                        <button className="btn btn-primary col-12" onClick={() => {
                            const cartProduct: Cart = {
                                _id: selectedProduct?._id,
                                description: description,
                                unitPrice: unitPrice,
                                qty: userQty,
                                total: (userQty * (unitPrice ? unitPrice : 0))
                            }
                            addToCart(cartProduct);
                        }}>+ add Product
                        </button>
                    </div>
                </div>
                <hr/>
                <div className="col-12">
                    <div className="row">
                        <div className="col-12">
                            <form className="col-12">
                                <input type="search" className="form-control" placeholder="Search Product here"/>
                            </form>
                        </div>
                    </div>
                </div>

                <br/>
                <div className="row">
                    <div className="col-12">
                        <table className='table table-hover table-bordered'>
                            <thead>
                            <tr>
                                <th>#Id</th>
                                <th>Product Name</th>
                                <th>Unit Price</th>
                                <th>Qty</th>
                                <th>Total</th>
                                <th>Delete Option</th>
                            </tr>
                            </thead>
                            <tbody>
                            {cart.map((data, index) => (
                                    <tr key={index}>
                                        <th>#{data._id}</th>
                                        <th>{data.description}</th>
                                        <th>{data.unitPrice}</th>
                                        <th>{data.qty}</th>
                                        <th>{data.total}</th>
                                        <th>
                                            <button
                                                onClick={(e) => {
                                                    setCart((prevState => prevState.filter((cartData) => cartData._id !== data._id)))
                                                    setTotal();
                                                }}
                                                className='btn btn-outline-danger btn-sm'>Remove
                                            </button>
                                        </th>
                                    </tr>
                                )
                            )}
                            </tbody>
                        </table>

                        <br/>

                        <div className="bottom-context" style={bottomContext}>
                            <div className="total-outer">
                                <h1 style={totalText}>
                                    Total :{netTotal}
                                </h1>
                            </div>
                            <div className="place-order-button-context">
                                <button className='btn btn-primary' onClick={async ()=>{
                                    const order = await axios.post('http://localhost:3000/api/v1/order/create/',{
                                        date: new Date(),
                                        customerDetails: selectedCustomer,
                                        totalCost: 1300,
                                        product: cart
                                    });
                                }}> Place Order</button>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
        </>

    )
}

export default Order;