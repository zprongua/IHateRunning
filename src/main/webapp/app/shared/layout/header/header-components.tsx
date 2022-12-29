import React from 'react';
import { Translate } from 'react-jhipster';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/IHR Logo.png" alt="Logo" />
  </div>
);

export const Brand = () => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    {/* <span className="brand-title">
      <Translate contentKey="global.title">IHateRunning</Translate>
    </span> */}
  </NavbarBrand>
);

export const Home = () => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </NavLink>
  </NavItem>
);

export const Races = () => (
  <NavItem>
    <NavLink tag={Link} to="/race" className="d-flex align-items-center">
      <FontAwesomeIcon icon="route" />
      <span>My Races</span>
    </NavLink>
  </NavItem>
);

export const Runs = () => (
  <NavItem>
    <NavLink tag={Link} to="/run" className="d-flex align-items-center">
      <FontAwesomeIcon icon="person-running" />
      <span>My Runs</span>
    </NavLink>
  </NavItem>
);
