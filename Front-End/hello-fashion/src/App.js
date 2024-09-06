import {Suspense} from "react";
import {HelmetProvider} from "react-helmet-async";
import {Toaster} from "react-hot-toast";
import Footer from "./layout/Footer/index";
import Header from "./layout/Header/index";
import AppRoutes from "./routes";
import FullBackLoader from "./common/components/Loaders/FullBackLoader";
import useUserProfile from "./common/hooks/user/useUserProfile";

function App() {

  useUserProfile();

  return (
      <HelmetProvider>
        <Toaster position="top-right" duration={2000} />
        <Suspense fallback={<FullBackLoader />}>
          <Header />
          <main>
            <AppRoutes />
          </main>
          <Footer />
        </Suspense>
      </HelmetProvider>
  );
}

export default App;
