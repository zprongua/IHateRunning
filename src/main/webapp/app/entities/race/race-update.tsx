import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IRace } from 'app/shared/model/race.model';
import { Distance } from 'app/shared/model/enumerations/distance.model';
import { getEntity, updateEntity, createEntity, reset } from './race.reducer';

export const RaceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const raceEntity = useAppSelector(state => state.race.entity);
  const loading = useAppSelector(state => state.race.loading);
  const updating = useAppSelector(state => state.race.updating);
  const updateSuccess = useAppSelector(state => state.race.updateSuccess);
  const distanceValues = Object.keys(Distance);

  const handleClose = () => {
    navigate('/race');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...raceEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          raceDistance: 'FIVEK',
          ...raceEntity,
          user: raceEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="iHateRunningApp.race.home.createOrEditLabel" data-cy="RaceCreateUpdateHeading">
            <Translate contentKey="iHateRunningApp.race.home.createOrEditLabel">Create or edit a Race</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {/* {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="race-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null} */}
              <ValidatedField
                label={translate('iHateRunningApp.race.raceName')}
                id="race-raceName"
                name="raceName"
                data-cy="raceName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('iHateRunningApp.race.raceDate')}
                id="race-raceDate"
                name="raceDate"
                data-cy="raceDate"
                type="date"
              />
              <ValidatedField
                label={translate('iHateRunningApp.race.raceDistance')}
                id="race-raceDistance"
                name="raceDistance"
                data-cy="raceDistance"
                type="select"
              >
                {distanceValues.map(distance => (
                  <option value={distance} key={distance}>
                    {translate('iHateRunningApp.Distance.' + distance)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="race-user" name="user" data-cy="user" label={translate('iHateRunningApp.race.user')} type="select">
                <option value="" key="1" />
                {users ? users.map(otherEntity => <option>{otherEntity.login}</option>) : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/race" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default RaceUpdate;
