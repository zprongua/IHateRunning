import './footer.scss';

import React from 'react';
import { Translate } from 'react-jhipster';
import { Col, Row } from 'reactstrap';

const Footer = () => (
  <div className="page-content rounded pad-5">
    <div className="fs-5">Zachary Prongua</div>
    <div className="fs-6 fw-lighter">
      <a href="http://linkedin.com/in/zprongua">LinkedIn</a> /<a href="http://github.com/zprongua"> GitHub</a>
    </div>
  </div>
);

export default Footer;
