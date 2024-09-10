import React from "react";
import {Container,Col,Row} from "reactstrap";
import logo from '../../assets/logo.png';
import paymentLogo from "../../assets/payment-method.png";
import {NavLink} from "react-router-dom";
import {
    FaEnvelope,
    FaFacebookF,
    FaInstagram,
    FaPhoneAlt,
    FaTiktok,
    FaTwitter,
} from "react-icons/fa";
import {MdKeyboardArrowRight} from "react-icons/md";
import {AiFillHome} from "react-icons/ai";
import SocialIcon from "../../common/components/Icons/SocialIcon";

const Footer = () => {
    return (
        <footer className="bg-light pt-4">
            {/* FooterInfo */}
            <Container>
                <Row>
                    <Col lg={6}> 
         
                        <div className="d-flex align-items-center">
                            {[
                                {Icon: FaFacebookF, bgColor: "#3b5998"},
                                {Icon: FaInstagram, bgColor: "#ac2bac"},
                                {Icon: FaTiktok, bgColor: "#000"},
                                {Icon: FaTwitter, bgColor: "#55acee"},
                            ].map((item, idx) => (
                                <SocialIcon key={idx} {...item} />
                            ))}
                        </div>
                    </Col>
                    <Col lg={6}>
                        <Row>
                            <Col md={6}>
                                <h5 className="mt-3">Information</h5>
                                <ul>
                                    {[
                                        "About us",
                                        "FAQ",
                                        "Terms & Conditions",
                                        "Contact Us",
                                        "Help",
                                    ].map((item) => (
                                        <li key={item} className="mb-2 d-flex align-items-center">
                                            <MdKeyboardArrowRight />
                                            <NavLink to="/" className={"nav-link"}>
                                                {item}
                                            </NavLink>
                                        </li>
                                    ))}
                                </ul>
                            </Col>
                            <Col md={6}>
                                <h5 className="mt-3">Contact</h5>
                                <ul>
                                    {[
                                        {Icon: AiFillHome, text: "habaraduwa,Galle,Sri Lanka"},
                                        {Icon: FaEnvelope, text: "info@hellofashion.com"},
                                        {Icon: FaPhoneAlt, text: "+ 94 71 017 82 10"},
                                    ].map(({Icon, text}) => (
                                        <li className="mb-3" key={text}>
                                            <Icon size={20} className="me-2" /> {text}
                                        </li>
                                    ))}
                                </ul>
                                <div>
                                    <img
                                        src={paymentLogo}
                                        alt="stripe-logo"
                                        width={"170px"}
                                        height={"24px"}
                                    />
                                </div>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </Container>

             {/*MadeText*/}
            <div className="text-center p-3 bg-light mt-3">
                © All Rights Reserved. Developed by <a  >D-Kavindu</a>.
            </div>
        </footer>
    );
}

export default Footer;