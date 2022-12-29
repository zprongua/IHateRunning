import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Race from './race';
import RaceDetail from './race-detail';
import RaceUpdate from './race-update';
import RaceDeleteDialog from './race-delete-dialog';

const RaceRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Race />} />
    <Route path="new" element={<RaceUpdate />} />
    <Route path=":id">
      <Route index element={<RaceDetail />} />
      <Route path="edit" element={<RaceUpdate />} />
      <Route path="delete" element={<RaceDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RaceRoutes;
