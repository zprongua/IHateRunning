import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Run from './run';
import RunDetail from './run-detail';
import RunUpdate from './run-update';
import RunDeleteDialog from './run-delete-dialog';

const RunRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Run />} />
    <Route path="new" element={<RunUpdate />} />
    <Route path=":id">
      <Route index element={<RunDetail />} />
      <Route path="edit" element={<RunUpdate />} />
      <Route path="delete" element={<RunDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RunRoutes;
