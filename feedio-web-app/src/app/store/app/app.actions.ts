import { createAction, props } from '@ngrx/store';
import { UserState } from './app.state';

export const ACTION_SET_LANGUAGE = createAction(
  '[App][User] Set language', props<{ language: string }>()
);

export const ACTION_SET_THEME = createAction(
  '[App][User] Set theme', props<{ theme: string }>()
);

export const ACTION_LOGIN = createAction(
  '[App][User] Login', props<{ user: UserState }>()
);

export const ACTION_LOGOUT = createAction(
  '[App][User] Logout'
);
