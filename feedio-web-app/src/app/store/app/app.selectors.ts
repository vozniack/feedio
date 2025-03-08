import { createFeatureSelector, createSelector } from '@ngrx/store';

export const SELECT_APP_STATE = createSelector(
  createFeatureSelector<any>('feedio-storage'), state => state
);

export const SELECT_LANGUAGE = createSelector(
  SELECT_APP_STATE, state => state.language
);

export const SELECT_THEME = createSelector(
  SELECT_APP_STATE, state => state.theme
);

export const SELECT_USER_STATE = createSelector(
  SELECT_APP_STATE, state => state.user
);
