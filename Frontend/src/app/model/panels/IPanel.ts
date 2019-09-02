export interface IPanel {
    type: string;
    dimensions: IDimension;
}

export interface IDimension {
    x: number;
    y: number;
    width: number;
    height: number;
}
