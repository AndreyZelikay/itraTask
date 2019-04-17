import { TestBed } from '@angular/core/testing';

import { TShirtService } from './tshirt-service.service';

describe('TShirtServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TShirtService = TestBed.get(TShirtService);
    expect(service).toBeTruthy();
  });
});
