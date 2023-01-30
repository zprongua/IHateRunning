import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table, Progress } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRun } from 'app/shared/model/run.model';
import { getEntities } from './run.reducer';

export const Run = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const runList = useAppSelector(state => state.run.entities);
  const loading = useAppSelector(state => state.run.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const runTotal = runList.reduce((totalMiles, run) => totalMiles + run.distance, 0);

  return (
    <div>
      <h4 id="run-heading" data-cy="RunHeading">
        {runTotal} of 238.86 (k miles to the moon)
        <Progress color="success" max={238.86} value={runTotal} />
        <div className="d-flex justify-content-end">
          {/* <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="iHateRunningApp.run.home.refreshListLabel">Refresh List</Translate>
          </Button> */}
        </div>
      </h4>
      <div className="table-responsive">
        {runList && runList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                {/* <th>
                  <Translate contentKey="iHateRunningApp.run.id">ID</Translate>
                </th> */}
                <th>
                  <Translate contentKey="iHateRunningApp.run.runName">Run Name</Translate>
                </th>
                <th>
                  <Translate contentKey="iHateRunningApp.run.runDate">Run Date</Translate>
                </th>
                <th>
                  <Translate contentKey="iHateRunningApp.run.distance">Distance</Translate>
                </th>
                <th>
                  <Translate contentKey="iHateRunningApp.run.time">Time</Translate>
                </th>
                <th>
                  <Translate contentKey="iHateRunningApp.run.pace">Pace</Translate>
                </th>
                <th className="d-flex justify-content-end">
                  <Link to="/run/new" className="btn btn-dark jh-create-entity btn-sm" id="jh-create-entity" data-cy="entityCreateButton">
                    <FontAwesomeIcon icon="plus" />
                    &nbsp; Run
                  </Link>
                </th>
                {/* <th>
                  <Translate contentKey="iHateRunningApp.run.user">User</Translate>
                </th> */}
              </tr>
            </thead>
            <tbody>
              {runList.map((run, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  {/* <td>
                    <Button tag={Link} to={`/run/${run.id}`} color="link" size="sm">
                      {run.id}
                    </Button>
                  </td> */}
                  <td>
                    <Button tag={Link} to={`/run/${run.id}/edit`} color="link" size="sm">
                      {run.runName}
                    </Button>
                  </td>
                  <td>{run.runDate ? <TextFormat type="date" value={run.runDate} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{run.distance} mi</td>
                  <td>{run.time}</td>
                  <td>{run.pace} min/mile</td>
                  {/* <td>{run.user ? run.user.login : ''}</td> */}
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      {/* <Button tag={Link} to={`/run/${run.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                        </span>
                      </Button> */}
                      <Button tag={Link} to={`/run/${run.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline"></span>
                      </Button>
                      <Button tag={Link} to={`/run/${run.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="iHateRunningApp.run.home.notFound">No Runs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Run;
