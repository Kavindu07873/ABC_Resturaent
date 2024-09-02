import React, {useEffect, useState} from "react";
import DefaultCard from "./cards/DefaultCard";
import DefaultChart from "./cards/DefaultChart";
import MinQtyCard from "./cards/MinQtyCard";
import Product from "./Product";
// import axios from "axios";
import AxiosInstance from '../config/axiosInstance.ts';

const Home:React.FC=()=>{

    // find all Products
    const [products, setProducts] = useState<Product[]>([])
    const [productsCount, setProductsCount] = useState<number | ''>()
    const [customerCount, setCustomerCount] = useState<number | ''>()
    const [orderCount, setOrderCount] = useState<number | ''>()


    //default trigger
    useEffect(() => {
        findAllMinProducts();
        findAllCount();
    }, [])

    const findAllMinProducts = async () => {
        try {
            const response = await AxiosInstance.get('/products/find-all-min');
            setProducts(response.data);
            console.log(products);
        } catch (e) {
            console.log(e)
        }
    }

    const findAllCount = async () => {
        try {
            const productCount = await AxiosInstance.get('/products/find-all-count');
            setProductsCount(productCount.data);
            console.log(productCount);
            const orderCount = await AxiosInstance.get('/order/find-all-count');
            setOrderCount(orderCount.data);
            console.log(orderCount);
            const customerCount = await AxiosInstance.get('/customer/find-all-count');
            setCustomerCount(customerCount.data);
            console.log(customerCount);

            const income =  await AxiosInstance.get('/order/find-income');
            console.log("income  : "+income.data);

        } catch (e) {
            console.log(e)
        }
    }


    const containerStyle: React.CSSProperties = {
        border: '2px solid Red'
    }

    const maxWidthStyle: React.CSSProperties = {
        maxWidth: '540px'
    }
    return (
        <>
            <br/>
            <div className="container">
                <div className="row">
                    <div className="col-12 col-sm-6 col-md-4 col-lg-3">
                        <DefaultCard
                            description='This is a wider card with supporting text below as a natural'
                            thumbnail='https://img.freepik.com/free-photo/medium-shot-people-shaking-hands_23-2149300663.jpg?w=360&t=st=1708501121~exp=1708501721~hmac=66000630e8f6ea5a9c644946455c8f8929f8e485ec621f31694884d95c41915c'
                            title='Customer'
                            value={customerCount}
                            key={1}
                        />
                    </div>
                    <div className="col-12 col-sm-6 col-md-4 col-lg-3">
                        <DefaultCard
                            description='This is a wider card with supporting text below as a natural'
                            thumbnail='https://img.freepik.com/free-photo/young-man-working-warehouse-with-boxes_1303-16616.jpg?w=360&t=st=1708501073~exp=1708501673~hmac=8257d4693c929d6c20ca58ccb6a00c3f1f36a2f2a6abc231c1ac1f11931d2ff1'
                            title='Product'
                            value={productsCount}
                            key={1}
                        />
                    </div>
                    <div className="col-12 col-sm-6 col-md-4 col-lg-3">
                        <DefaultCard
                            description=' lead-in to additional content. This content is a little bit longer.'
                            thumbnail='https://img.freepik.com/free-photo/young-woman-delivering-order_23-2148964001.jpg?w=360&t=st=1708500722~exp=1708501322~hmac=bfe22a3733de729919a9adc52f606a83fbd30634c0cda67baa166cf563c1d1c5'
                            title='Orders'
                            value={orderCount}
                            key={1}
                        />
                    </div>
                    <div className="col-12 col-sm-6 col-md-4 col-lg-3">
                        <DefaultCard
                            description='This is a wider card with supporting text below as a natural'
                            thumbnail='https://img.freepik.com/free-photo/person-carrying-lot-cash_53876-65367.jpg?w=360&t=st=1708495176~exp=1708495776~hmac=7087bb441faf2dcb8978071d05e6dc845507cedb4a21bed36bd422cfa3aa7f23'
                            title='Income'
                            value={250}
                            key={1}
                        />
                    </div>
                </div>
                <br/>
                <div className="row">
                    <div className="col-12 col-md-9">
                        <div className="context">
                            <DefaultChart/>
                        </div>
                    </div>
                    <div className="col-12 col-md-3">
                        {products.map((prod,index)=>
                            <MinQtyCard name={prod.name} description={prod.description} image={prod.image} key={index}/>
                        )}
                    </div>
                </div>
            </div>
        </>
    )
}

export default Home;