import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './run.reducer';

export const RunDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const runEntity = useAppSelector(state => state.run.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="runDetailsHeading">
          <Translate contentKey="iHateRunningApp.run.detail.title">Run</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{runEntity.id}</dd>
          <dt>
            <span id="runName">
              <Translate contentKey="iHateRunningApp.run.runName">Run Name</Translate>
            </span>
          </dt>
          <dd>{runEntity.runName}</dd>
          <dt>
            <span id="runDate">
              <Translate contentKey="iHateRunningApp.run.runDate">Run Date</Translate>
            </span>
          </dt>
          <dd>{runEntity.runDate ? <TextFormat value={runEntity.runDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="distance">
              <Translate contentKey="iHateRunningApp.run.distance">Distance</Translate>
            </span>
          </dt>
          <dd>{runEntity.distance}</dd>
          <dt>
            <span id="time">
              <Translate contentKey="iHateRunningApp.run.time">Time</Translate>
            </span>
          </dt>
          <dd>{runEntity.time}</dd>
          <dt>
            <span id="pace">
              <Translate contentKey="iHateRunningApp.run.pace">Pace</Translate>
            </span>
          </dt>
          <dd>{runEntity.pace}</dd>
          <dt>
            <Translate contentKey="iHateRunningApp.run.user">User</Translate>
          </dt>
          <dd>{runEntity.user ? runEntity.user.login : ''}</dd>
        </dl>
        <Button tag={Link} to="/run" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/run/${runEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RunDetail;
