package stepDefinitions;

enum  bookingsSchemas
{

    ErrorResponse,EMPTY;

    @Override
    public String toString()
    {
        return this == EMPTY ?"":this.name();
    }

}
