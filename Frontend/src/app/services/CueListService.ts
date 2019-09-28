import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ICueList } from '../model/panels/ICueList';

@Injectable()
export class CueListService {

  constructor(private http: HttpClient) {}

  public getCueLists(): Promise<ICueList[]> {
    return this.http
      .get('/api/cuelist')
      .toPromise() as Promise<ICueList[]>;
  }
}
