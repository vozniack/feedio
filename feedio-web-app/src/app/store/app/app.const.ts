import { AppState } from './app.state';

export const initialAppState = (): AppState => {
  return {
    language: 'en',
    theme: 'theme-light',
    user: {
      token: undefined
    }
  };
};
