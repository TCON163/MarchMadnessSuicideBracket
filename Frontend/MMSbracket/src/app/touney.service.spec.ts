import { TestBed } from '@angular/core/testing';

import { TouneyService } from './tourney.service';

describe('TouneyService', () => {
  let service: TouneyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TouneyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
