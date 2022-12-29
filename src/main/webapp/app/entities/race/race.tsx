import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRace } from 'app/shared/model/race.model';
import { getEntities } from './race.reducer';

export const Race = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const raceList = useAppSelector(state => state.race.entities);
  const loading = useAppSelector(state => state.race.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="race-heading" data-cy="RaceHeading">
        <Translate contentKey="iHateRunningApp.race.home.title">Races</Translate>
        <div className="d-flex justify-content-end">
          {/* <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="iHateRunningApp.race.home.refreshListLabel">Refresh List</Translate>
          </Button> */}
          <Link to="/race/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Race
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {raceList && raceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                {/* <th>
                  <Translate contentKey="iHateRunningApp.race.id">ID</Translate>
                </th> */}
                <th>
                  <Translate contentKey="iHateRunningApp.race.raceName">Race Name</Translate>
                </th>
                <th>
                  <Translate contentKey="iHateRunningApp.race.raceDate">Race Date</Translate>
                </th>
                <th>
                  <Translate contentKey="iHateRunningApp.race.raceDistance">Race Distance</Translate>
                </th>
                {/* <th>
                  <Translate contentKey="iHateRunningApp.race.user">User</Translate>
                </th> */}
                <th />
              </tr>
            </thead>
            <tbody>
              {raceList.map((race, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  {/* <td>
                    <Button tag={Link} to={`/race/${race.id}`} color="link" size="sm">
                      {race.id}
                    </Button>
                  </td> */}
                  <td>
                    <Button tag={Link} to={`/race/${race.id}`} color="link" size="sm">
                      {race.raceName}
                    </Button>
                  </td>
                  <td>{race.raceDate ? <TextFormat type="date" value={race.raceDate} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>
                    <Translate contentKey={`iHateRunningApp.Distance.${race.raceDistance}`} />
                  </td>
                  {/* <td>{race.user ? race.user.login : ''}</td> */}
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      {/* <Button tag={Link} to={`/race/${race.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                        </span>
                      </Button> */}
                      <Button tag={Link} to={`/race/${race.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline"></span>
                      </Button>
                      <Button tag={Link} to={`/race/${race.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline"></span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="iHateRunningApp.race.home.notFound">No Races found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Race;
