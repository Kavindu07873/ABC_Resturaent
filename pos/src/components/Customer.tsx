import React, {useEffect, useState} from "react";
import {Modal} from "react-bootstrap";
// import axios from "axios";
import AxiosInstance from '../config/axiosInstance.ts';


interface Customer {
    id: string,
    name: string,
    address: string,
    salary: number
}

const Customer: React.FC = () => {

    // find all Customer
    const [customers, setCustomers] = useState<Customer[]>([])

    //save Customer
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [salary, setSalary] = useState<number | ''>('');


    //update Customer
    const [selectedCustomerId, setSelectedCustomerId] = useState('');
    const [updateName, setUpdateName] = useState('');
    const [updateAddress, setUpdateAddress] = useState('');
    const [updateSalary, setUpdateSalary] = useState<number | ''>('');

    //model state
    const [modelState, setModelState] = useState<boolean>(false);


    //default trigger
    useEffect(() => {
        findAllCustomers();
    }, [])

    const findAllCustomers = async () => {
        try {
            const response = await AxiosInstance.get('/customer/find-all?searchText=&page=1&size=4');
            setCustomers(response.data);
            console.log(customers);
        } catch (e) {
            console.log(e)
        }
    }

    const saveCustomer = async () => {
        console.log(name, address, salary)
        try {
            const response = await AxiosInstance.post('/customer/create', {
                name, address, salary
            });
            console.log(response);

            setName('');
            setAddress('');
            setSalary('');
            findAllCustomers()
        } catch (error) {
            console.log(error)
        }
    }

    const deleteCustomer = async (id) => {
        console.log("id : " + id)
        const response = await AxiosInstance.delete('/customer/delete-by-id/' + id);
        // setCustomers(response.data);
        // console.log(customers)
        findAllCustomers();
    }

    const loadModel = async (id) => {
        const customer = await AxiosInstance.get('/customer/find-by-id/' + id);
        console.log(customer.data)
        setSelectedCustomerId(customer.data._id)
        setUpdateName(customer.data.name);
        setUpdateAddress(customer.data.address);
        setUpdateSalary(parseFloat(customer.data.salary));
        setModelState(true);
    }

    const updateCustomer = async () => {
        console.log(name, address, salary)
        try {
            console.log(selectedCustomerId)
            const response = await AxiosInstance.put('/customer/update/' + selectedCustomerId, {
                name: updateName, address: updateAddress, salary: updateSalary
            });
            console.log(response);

            setModelState(false);
            findAllCustomers();

        } catch (error) {
            console.log(error)
        }

    }

    return (
        <>
            <br/>
            <div className="container">
                <div className="row">
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="customerName"> Customer Name</label>
                            <input value={name} onChange={(e => {
                                setName(e.target.value)
                            })} type="text" className='form-control' id='customerName'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="customerAddress"> Customer Address</label>
                            <input value={address} onChange={(e => {
                                setAddress(e.target.value)
                            })} type="text" className='form-control' id='customerAddress'/>
                        </div>
                    </div>
                    <div className="col-12 col-sm-6 col-md-4">
                        <div className="form-group">
                            <label htmlFor="customerSalary"> Customer Salary</label>
                            <input value={salary} onChange={(e => {
                                setSalary(e.target.value == '' ? '' : parseFloat(e.target.value))
                            })} type="text" className='form-control' id='customerSalary'/>
                        </div>
                    </div>
                </div>
                <br/>
                <div className="row">
                    <div className="col-12">
                        <button onClick={saveCustomer} className="btn btn-primary col-12">Save Customer</button>
                    </div>
                </div>
                <hr/>
                <div className="col-12">
                    <div className="row">
                        <div className="col-12">
                            <form className="col-12">
                                <input type="search" className="form-control" placeholder="Search Customer here"/>
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
                                <th>Customer Name</th>
                                <th>Address</th>
                                <th>Salary</th>
                                <th>Delete Option</th>
                                <th>Update Option</th>
                            </tr>
                            </thead>
                            <tbody>
                            {customers.map((customer, index) =>
                                <tr key={index}>
                                    <th>#{index}</th>
                                    <th>{customer.name}</th>
                                    <th>{customer.address}</th>
                                    <th>{customer.salary}</th>
                                    <th>
                                        <button
                                            onClick={(e) => {
                                                if (confirm('are you sure?')) {
                                                    deleteCustomer(customer._id)
                                                }
                                            }}
                                            className='btn btn-outline-danger btn-sm'>Delete
                                        </button>
                                    </th>
                                    <th>
                                        <button
                                            onClick={(e) => {
                                                loadModel(customer._id)
                                            }}
                                            className='btn btn-outline-warning btn-sm'>Update
                                        </button>
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
                    <h2>Update Customer</h2>
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
                            <input type="text" defaultValue={updateAddress}
                                   onChange={(e) => setUpdateAddress(e.target.value)}
                                   className='form-control'/>
                        </div>
                        <br/>
                    </div>
                    <div className="col-12">
                        <div className="form-group">
                            <input type="text" defaultValue={updateSalary}
                                   onChange={(e) => setUpdateSalary(parseFloat(e.target.value))}
                                   className='form-control'/>
                        </div>
                        <br/>
                    </div>
                    <div className="col-12">
                        <button type='button' className='btn-success btn col-12'
                                onClick={() => {
                                    updateCustomer()
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
};

export default Customer;