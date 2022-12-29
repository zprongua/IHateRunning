import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './race.reducer';

export const RaceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const raceEntity = useAppSelector(state => state.race.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="raceDetailsHeading">
          <Translate contentKey="iHateRunningApp.race.detail.title">Race</Translate>
        </h2>
        <dl className="jh-entity-details">
          {/* <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{raceEntity.id}</dd> */}
          <dt>
            <span id="raceName">
              <Translate contentKey="iHateRunningApp.race.raceName">Race Name</Translate>
            </span>
          </dt>
          <dd>{raceEntity.raceName}</dd>
          <dt>
            <span id="raceDate">
              <Translate contentKey="iHateRunningApp.race.raceDate">Race Date</Translate>
            </span>
          </dt>
          <dd>{raceEntity.raceDate ? <TextFormat value={raceEntity.raceDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="raceDistance">
              <Translate contentKey="iHateRunningApp.race.raceDistance">Race Distance</Translate>
            </span>
          </dt>
          <dd>{raceEntity.raceDistance}</dd>
          {/* <dt>
            <Translate contentKey="iHateRunningApp.race.user">User</Translate>
          </dt>
          <dd>{raceEntity.user ? raceEntity.user.login : ''}</dd> */}
        </dl>
        <Button tag={Link} to="/race" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/race/${raceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RaceDetail;
