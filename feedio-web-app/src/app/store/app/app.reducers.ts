import { AppState, UserState } from './app.state';
import { initialAppState } from './app.const';
import { Action, createReducer, on } from '@ngrx/store';
import { ACTION_SET_LANGUAGE, ACTION_SET_THEME, ACTION_LOGIN, ACTION_LOGOUT, } from './app.actions';

export const initialState = initialAppState();

export function appReducer(state: AppState = initialState, action: Action) {
  return _appReducer(state, action);
}

const _appReducer = createReducer(initialState,
  on(ACTION_SET_LANGUAGE, (state, newState) => onSetLanguage(state, newState.language)),
  on(ACTION_SET_THEME, (state, newState) => onSetTheme(state, newState.theme)),
  on(ACTION_LOGIN, (state, newState) => onSetUserState(state, newState.user)),
  on(ACTION_LOGOUT, (state) => onSetUserState(state, {})),
);

function onSetLanguage(state: AppState, language: string) {
  return {
    ...state,
    language: language
  };
}

function onSetTheme(state: AppState, theme: string) {
  return {
    ...state,
    theme: theme
  };
}

function onSetUserState(state: AppState, user: UserState) {
  return {
    ...state,
    user: user
  };
}
