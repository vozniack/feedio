import { AppState } from './app.state';

export const initialAppState = (): AppState => {
  return {
    language: 'en',
    theme: 'light',
    user: {
      token: undefined
    }
  };
};
