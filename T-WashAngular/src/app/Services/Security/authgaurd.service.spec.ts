import { TestBed } from '@angular/core/testing';

import { AuthGaurdService } from './authgaurd.service';

describe('AuthgaurdService', () => {
  let service: AuthGaurdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGaurdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
