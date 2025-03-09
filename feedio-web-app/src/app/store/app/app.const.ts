import { AppState } from './app.state';

export const initialAppState = (): AppState => {
  return {
    language: navigator.language.replace('-', '_'),
    theme: 'theme-light',
    user: {
      token: undefined
    }
  };
};
