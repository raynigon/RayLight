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

  public createCueList(cuelist: ICueList) {
    return this.http
      .post('/api/cuelist', cuelist)
      .toPromise() as Promise<ICueList>;
  }

  public createCue(cuelistId: number, cue: any) {
    return this.http
      .post(`/api/cuelist/${cuelistId}/cue`, cue)
      .toPromise() as Promise<ICueList>;
  }

  public updateCue(cuelistId: number, cue: any) {
    return this.http
      .put(`/api/cuelist/${cuelistId}/cue`, cue)
      .toPromise() as Promise<ICueList>;
  }

  public deleteCue(cuelistId: any, cueId: any) {
    return this.http
    .delete(`/api/cuelist/${cuelistId}/cue/${cueId}`)
    .toPromise();
  }

  public deleteCueList(cuelist: ICueList) {
    return this.http
      .delete(`/api/cuelist/${cuelist.id}`)
      .toPromise();
  }
}
