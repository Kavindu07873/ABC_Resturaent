import React from "react";
import {useDispatch} from "react-redux";
import {Col, Container, Row} from "reactstrap";
import {MdAddPhotoAlternate, MdAutoAwesomeMotion, MdLogout, MdPeopleAlt, MdWidgets} from 'react-icons/md';
import {FaBoxes} from "react-icons/fa";
import {RiCoupon2Fill} from 'react-icons/ri'
import SidebarLink from "../../common/components/Shared/SidebarLink";
import Sidebar from "../../common/components/Shared/Sidebar";
import {logout} from "../../features/user/userSlice";

const DashboardLayout = ({children}) => {
    const dispatch = useDispatch();
    return (
        <Container className="py-4">
            <Row className="position-relative">
                <Col lg={1} md={2} xs={2}>
                    <Sidebar>
                        <SidebarLink
                            Icon={MdAutoAwesomeMotion}
                            name={"Products"}
                            linkUrl={"/admin/products"}
                        />
                        <SidebarLink
                            Icon={MdPeopleAlt}
                            name={"Users"}
                            linkUrl={"/admin/users"}
                        />
                        <SidebarLink
                            Icon={FaBoxes}
                            name={"Orders"}
                            linkUrl={"/admin/orders"}
                        />
                        <SidebarLink
                            Icon={RiCoupon2Fill}
                            name={"Coupons"}
                            linkUrl={"/admin/coupons"}
                        />
                        <SidebarLink
                            Icon={MdWidgets}
                            name={"Categories"}
                            linkUrl={"/admin/categories"}
                        />
                        <SidebarLink
                            Icon={MdAddPhotoAlternate}
                            name={"Banners"}
                            linkUrl={"/admin/banners"}
                        />
                        <SidebarLink
                            Icon={MdLogout}
                            name={"Logout"}
                            handleClick={() => dispatch(logout())}
                        />
                    </Sidebar>
                </Col>
                <Col lg={11} md={10} xs={10} className="bg-light p-3 rounded">
                    {children}
                </Col>
            </Row>
        </Container>
    );
}

export default DashboardLayout