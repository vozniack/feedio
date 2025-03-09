export interface SignupRequest {

  email: string;
  password: string;

  firstName: string;
  lastName: string;

  language: String;
}

export interface SignupResponse {

  success: boolean;
}

export interface LoginRequest {

  email: string;
  password: string;
}

export interface LoginResponse {

  token: string;
}
