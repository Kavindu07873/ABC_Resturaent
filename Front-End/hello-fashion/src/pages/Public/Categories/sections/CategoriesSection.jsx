import React from "react";
import {Link} from "react-router-dom";
import {Alert, Col, Row} from "reactstrap";
import BlockLoader from "../../../../common/components/Loaders/BlockLoader";
import ImageLoader from "../../../../common/components/Loaders/ImgLoader";
import useGetCategories from "../../../../common/hooks/categories/useGetCategories";

const CategoriesSection = ({withSlice}) => {
  const {allCategories} = useGetCategories();

  const categories = withSlice 
    ? allCategories?.categories?.slice(0, 4) 
    : allCategories?.categories;

  // Log the data to verify loading and categories state
  console.log("allCategories loading: ", allCategories?.loading);
  console.log("Categories: ", categories);
  return (
    <section className="categories-section">
      {allCategories?.loading ? (
        <BlockLoader minHeight={300} />
      ) : allCategories?.error ? (
        <Alert color="danger">{allCategories?.error}</Alert>
      ) : categories && categories.length > 0 ? (
        <Row md={4} xs={2}>
          {categories.map((item, idx) => (
            <Col key={idx} className="mb-4">
              <Link to={`/categories/${item.id}`}> {/* Ensure correct ID property */}
                <ImageLoader
                  image={item?.image || defaultImageUrl} // Use default image if none provided
                  style={{width: "100%", height: "auto"}}
                  name={item.name}
                  description ={item.description}
                  id= {item.id}
                />
              </Link>
            </Col>
          ))}
        </Row>
      ) : (
        <Alert color="warning">No categories found</Alert>
      )}
    </section>
  );
};

export default CategoriesSection;

