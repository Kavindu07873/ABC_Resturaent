import React, {ChangeEvent, useEffect, useState} from "react";
import {Modal} from "react-bootstrap";
// import axios from "axios";
// import {storage} from "../config/firebase";
// import firebase from 'firebase/app';
import AxiosInstance from '../config/axiosInstance.ts';
// import * as events from "node:events";
// import firebase from "firebase/compat";
// import storage = firebase.storage;
// import * as url from "node:url";
import {storage} from "../config/firebase.ts";

interface Product {
    id: string,
    name: string,
    description: string,
    image: string,
    unitPrice: number,
    qtyOnHand: number
}

const Product: React.FC = () => {


// find all Products
    const [products, setProducts] = useState<Product[]>([])

// set Image state
    const [image, setImage] = useState<File | null>(null);

    const handleFile = async (event: React.ChangeEvent<HTMLInputElement>) => {
        setImage(event.target.files[0]);
    }

//save Products
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [unitPrice, setUnitePrice] = useState<number | ''>('');
    const [qtyOnHand, setQtyOnHand] = useState<number | ''>('');


//update Products
    const [selectedProductsId, setSelectedProductsId] = useState('');
    const [updateName, setUpdateName] = useState('');
    const [updateDescription, setUpdateDescription] = useState('');
    const [updateImage, setUpdateImage] = useState('');
    const [updateUnitePrice, setUpdateUnitePrice] = useState<number | ''>('');
    const [updateQtyOnHand, setUpdateQtyOnHand] = useState<number | ''>('');

//model state
    const [modelState, setModelState] = useState<boolean>(false);

//default trigger
    useEffect(() => {
        findAllProducts();
    }, [])

// handle Image
//     const handleImageChange = (e: ChangeEvent<HTMLInputElement>) => {
//         if (e.target.files && e.target.files[0]) {
//             setImage(e.target.files[0]);
//         }
//     }

// save product

    const saveProduct = async () => {
        console.log(name, description, unitPrice, qtyOnHand)
        let imageUrl = 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D';

        if (image) {
            try {
                const storageRef = storage.ref();
                const imageRef = storageRef.child(`images/${Math.random() + '-' + image.name}`);
                const snapShot = await imageRef.put(image);
                imageUrl = await snapShot.ref.getDownloadURL();

                try {
                    const response = await AxiosInstance.post('/products/create', {
                        name, description, unitPrice, qtyOnHand, image: imageUrl
                    });
                    console.log(response);

                    setName('');
                    setDescription('');
                    setUnitePrice('');
                    setQtyOnHand('')
                    findAllProducts()
                } catch (error) {
                    console.log(error)
                }

            }catch (e){
                console.log(e)
            }
        }
    }

    const deleteProduct = async (id) => {
        console.log("id : " + id)
        const response = await AxiosInstance.delete('/products/delete-by-id/' + id);
        // setProducts(response.data);
        console.log(response)
        findAllProducts();
    }

    const loadModel = async (id) => {
        const product = await AxiosInstance.get('/products/find-by-id/' + id);
        console.log(product.data)
        setSelectedProductsId(product.data._id)
        setUpdateName(product.data.name);
        setUpdateImage(product.data.image);
        setUpdateDescription(product.data.description);
        setUpdateQtyOnHand(parseFloat(product.data.qtyOnHand));
        setUpdateUnitePrice(parseFloat(product.data.unitPrice));

        setModelState(true);
    }

    const updateProduct = async () => {
        const imageUrl = 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZHVjdHxlbnwwfHwwfHx8MA%3D%3D';
        console.log(name, description, unitPrice, qtyOnHand)
        try {
            console.log(selectedProductsId)
            const response = await AxiosInstance.put('/products/update/' + selectedProductsId, {
                name: updateName,
                description: updateDescription,
                unitPrice: updateUnitePrice,
                qtyOnHand: updateQtyOnHand,
                image: imageUrl
            });
            console.log(response);

            setModelState(false);
            findAllProducts();
        } catch (error) {
            console.log(error)
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


    const styleObj: React.CSSProperties = {
        marginTop: '10px'
    }


    return (
        <>
            <br/>
            <div className="container">
                <div className="row">
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="productName" style={styleObj}> Product Name</label>
                            <input onChange={(e => {
                                setName(e.target.value)
                            })} type="text" className='form-control' id='productName'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="price" style={styleObj}>Unit Price</label>
                            <input onChange={(e => {
                                setUnitePrice(parseFloat(e.target.value))
                            })} type="text" className='form-control' id='price'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="qty" style={styleObj}>Qty On Hand</label>
                            <input onChange={(e => {
                                setQtyOnHand(parseFloat(e.target.value))
                            })} type="text" className='form-control' id='qty'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="image" style={styleObj}>Product Image</label>
                            <input onChange={handleFile}
                                   type="file" className='form-control' id='image'/>
                        </div>
                    </div>

                    <div className="col-12">
                        <div className="form-group">
                            <label htmlFor="description" style={styleObj}> Description</label>
                            <textarea onChange={(e => {
                                setDescription(e.target.value)
                            })} rows={5} className='form-control' id='description'/>
                        </div>
                    </div>
                </div>
                <br/>
                <div className="row">
                    <div className="col-12">
                        <button onClick={saveProduct} className="btn btn-primary col-12">Save Product</button>
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
                                <th>Qty On Hand</th>
                                <th>Unit Price</th>
                                <th>Delete Option</th>
                                <th>Update Option</th>
                                <th>see more</th>
                            </tr>
                            </thead>
                            <tbody>
                            {products.map((product, index) =>

                                <tr key={index}>
                                    <th>#{index}</th>
                                    <th>{product.name}</th>
                                    <th>{product.qtyOnHand}</th>
                                    <th>{product.unitPrice}</th>
                                    <th>
                                        <button className='btn btn-outline-danger btn-sm'
                                                onClick={(e) => {
                                                    if (confirm('are you sure?')) {
                                                        deleteProduct(product._id)
                                                    }
                                                }}
                                        >Delete
                                        </button>
                                    </th>
                                    <th>
                                        <button
                                            onClick={(e) => {
                                                loadModel(product._id)
                                            }}
                                            className='btn btn-outline-warning btn-sm'>Update
                                        </button>
                                    </th>
                                    <th>
                                        <button className='btn btn-outline-info btn-sm'>View</button>
                                    </th>
                                </tr>
                            )}
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

            <Modal show={modelState}>
                <div className="p-4">
                    <h2>Update Product</h2>
                    <hr/>
                    <div className="col-12">
                        <div className="form-group">
                            <input type="text" defaultValue={updateName}
                                   onChange={(e) => setUpdateName(e.target.value)}
                                   className='form-control'/>
                        </div>
                        <br/>
                    </div>
                    <div className="col-12">
                        <div className="form-group">
                            <input type="text" defaultValue={updateQtyOnHand}
                                   onChange={(e) => setUpdateQtyOnHand(parseFloat(e.target.value))}
                                   className='form-control'/>
                        </div>
                        <br/>
                    </div>
                    <div className="col-12">
                        <div className="form-group">
                            <input type="text" defaultValue={updateUnitePrice}
                                   onChange={(e) => setUpdateUnitePrice(parseFloat(e.target.value))}
                                   className='form-control'/>
                        </div>
                        <br/>
                    </div>
                    <div className="col-12">
                        <button type='button' className='btn-success btn col-12'
                                onClick={() => {
                                    updateProduct()
                                }}
                        >Update Customer
                        </button>
                        <br/>
                        <br/>
                        <button type='button' className='btn-secondary btn col-12'
                                onClick={() => setModelState(false)}> Close Model
                        </button>
                    </div>
                </div>
            </Modal>

        </>

    )
}

export default Product;