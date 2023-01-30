import './home.scss';

import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert } from 'reactstrap';
import { useAppSelector } from 'app/config/store';
import { App } from '../search/search';
// const [error, setError] = useState(null);
// const [isLoaded, setIsLoaded] = useState(false);
// const [q, setQ] = useState("");

// function App() {
//   const [error, setError] = useState(null);
//   const [isLoaded, setIsLoaded] = useState(false);
//   const [races, setRaces] = useState([]);

//   useEffect(() => {
//       fetch("https://runsignup.com/rest/races?format=json&state=de&" +
//               "oauth_consumer_key=b634009053a02e18ff74db8386566a79210355bf&" +
//               "oauth_signature_method=HMAC-SHA1&oauth_timestamp=1672787093&" +
//               "oauth_nonce=R9ALEhP11NL&oauth_version=1.0&" +
//               "oauth_callback=http%3A%2F%2Flocalhost%3A8080&" +
//               "oauth_signature=f4bYqQt0N3kQlXh13wuDpAhuyQ4%3D")
//           .then((res) => res.json())
//           .then(
//               (result) => {
//                   setIsLoaded(true);
//                   setRaces(result);
//               },
//               (error) => {
//                   setIsLoaded(true);
//                   setError(error);
//               }
//           );
//   }, []);

//   if (error) {
//       return <>{error.message}</>;
//   } else if (!isLoaded) {
//       return <>loading...</>;
//   } else {
//       return (
//           <div className="wrapper">
//               <div className="search-wrapper">
//                       <label htmlFor="search-form">
//                           <input
//                               type="search"
//                               name="search-form"
//                               id="search-form"
//                               className="search-input"
//                               placeholder="Search for..."
//                               value={q}
//                               onChange={(e) => setQ(e.target.value)}
//                           />
//                           <span className="sr-only">Search countries here</span>
//                       </label>
//                   </div>
//               <ul className="card-grid">
//                   {races.map((race) => (
//                       <li>
//                           <article className="card" key={race.race_id}>
//                               <div className="card-content">
//                                   <h2 className="card-name">{race.name}</h2>
//                                   <ol className="card-list">
//                                       <li>
//                                           Date:{" "}
//                                           <span>{race.next_date}</span>
//                                       </li>
//                                       <li>
//                                           URL: <span>{race.external_race_url}</span>
//                                       </li>
//                                       <li>
//                                           Location: <span>{race.address.city}, {race.address.state}</span>
//                                       </li>
//                                       <li>
//                                           Description: <span>{race.description}</span>
//                                       </li>
//                                   </ol>
//                               </div>
//                           </article>
//                       </li>
//                   ))}
//               </ul>
//           </div>
//       );
//   }
// }

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  return (
    <div>
      <div className="container">
        <span className="logo rounded" />
      </div>
      <div className="row offset-center">
        <div className="col-md-8 offset-md-2">
          <form className="d-flex" id="defineform">
            <input className="form-control me-2" type="search" name="search" id="search" placeholder="Location" aria-label="Search" />
            <button className="btn btn-secondary col-md-2" type="submit">
              Go!
            </button>
          </form>
        </div>
        <p className="text-center">Search for a race to begin your journey.</p>
      </div>
      {/* {account?.login ? (
          <div>
            <Alert color="success">
              <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                You are logged in as user {account.login}.
              </Translate>
            </Alert>
          </div>
        ) : (
          <div>
            <Alert color="warning">
              <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>

              <Link to="/login" className="alert-link">
                <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
              </Link>
              <Translate contentKey="global.messages.info.authenticated.suffix">
                , you can try the default accounts:
                <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
              </Translate>
            </Alert>

            <Alert color="warning">
              <Translate contentKey="global.messages.info.register.noaccount">You do not have an account yet?</Translate>&nbsp;
              <Link to="/account/register" className="alert-link">
                <Translate contentKey="global.messages.info.register.link">Register a new account</Translate>
              </Link>
            </Alert>
          </div>
        )} */}
    </div>
  );
};

export default Home;
