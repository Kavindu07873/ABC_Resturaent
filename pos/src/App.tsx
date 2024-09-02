import {useState} from 'react'
import {BrowserRouter as Router, Routes, Route,Link} from "react-router-dom";
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import Customer from "./components/Customer";
import Product from "./components/Product";
import Order from "./components/Order";
import Home from "./components/Home";
import Login from "./components/Login"
import Signup from "./components/Signup.tsx";
import Calculator from "./components/Calculator.tsx";


function App() {
    const [count, setCount] = useState(0)

    return (
        <Router>
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <div className="container-fluid">
                        <div className="navbar-brand" >
                                <img src='data:image/png;base64,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'
                                     alt="icon_image" className="logo"/>
                        </div>
                        <button className="navbar-toggler"  type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarNav">
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    <Link className="nav-link active" aria-current="page" to='/'>Home</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to='/customer'>Customer</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to='/product'>Products</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link " to='/orders'>Order Management</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link " to='/login'>Login </Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link " to='/calculator'>Calculator </Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

                <Routes>
                    <Route path='/' element={<Home/>}/>
                    <Route path='/customer' element={<Customer/>}/>
                    <Route path='/product' element={<Product/>}/>
                    <Route path='/orders' element={<Order/>}/>
                    <Route path='/login' element={<Login/>}/>
                    <Route path='/signup' element={<Signup/>}/>
                    <Route path='/calculator' element={<Calculator/>}/>

                </Routes>

            </div>
        </Router>
    )
}

export default App
