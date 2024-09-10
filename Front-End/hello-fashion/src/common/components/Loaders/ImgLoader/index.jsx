import React, {useState} from "react";
import BlockLoader from "../BlockLoader";

const ImageLoader = ({image, style, name ,description , _id}) => {
  const [isImgLoaded, setIsImgLoaded] = useState(false);
  const handleImageLoaded = () => {
    setIsImgLoaded(true);
  };
  console.log("Image : ",image)
  console.log("name : ",name)
  console.log("description : ",description)
  console.log("id : ",_id)
  image = "https://www.allrecipes.com/thmb/2rPJp4sRMmSa-5MgBRuHz8XDxlc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/233446-lo-mein-noodles-dmfs-2x1-1356-1490-62771eb9cd6840fdb75f332303fff7b1.jpg"
 
  return (
    <>
      {!isImgLoaded && <BlockLoader />}
      <div>
      <img
        src={image}
        alt="item-img"
        style={{ display: !isImgLoaded ? "none" : "inline", ...style }}
        onLoad={handleImageLoaded}
      />
      </div>
      <h5 class="my-3" >{name}</h5>
      <h5 style={{ height: '24px', fontSize: '10px' }}>{description}</h5>
    </>
  );
  
};

export default ImageLoader;
