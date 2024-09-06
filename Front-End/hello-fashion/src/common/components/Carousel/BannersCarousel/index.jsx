import React from "react";
import {Carousel} from "react-responsive-carousel";
import {Alert} from "reactstrap";
import useGetBanners from "../../../hooks/banners/useGetBanners";
import BlockLoader from "../../Loaders/BlockLoader";
import logo from "../../../../assets/bannerABc.jpg";

const BannersCarousel = () => {
  // const {allBanners} = useGetBanners();
  const allBanners = [
    {image: logo}];
  return (
    <>
          <Carousel
            showArrows={true}
            showStatus={false}
            showThumbs={false}
            transitionTime={800}
            autoPlay
            infiniteLoop
            interval={3000}
          >
            {allBanners?.map((item, idx) => (
              <div key={idx}>
                <img
                  src={item?.image}
                  style={{
                    width: "100%",
                    height: "100%",
                    // objectFit: "cover",
                  }}
                  alt={"banner-img"}
                />
              </div>
            ))}
          </Carousel>
        </>
  )
};

export default BannersCarousel;
