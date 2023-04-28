import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Multiselect from 'multiselect-react-dropdown';
import HealthAndSafetyIcon from '@mui/icons-material/HealthAndSafety';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { CircularProgress, IconButton, MenuItem, Slide } from '@mui/material';
import { Close } from '@mui/icons-material';
import ReactJson from 'react-json-view'
import { JSONTree } from 'react-json-tree';
import { Graph } from 'react-json-graph';
import { JsonToTable } from "react-json-to-table";


const theme = createTheme();
export default function Home() {
  const [result, setResult] = React.useState();
  const [checked, setChecked] = React.useState(false);
  const [loading, setLoading] = React.useState(false);
  const [query, setQuery] = React.useState("");
  const [queryName, setQueryName] = React.useState("");
  const [bg, setBg] = React.useState();
  const containerRef = React.useRef(null);
  const [select, setSelect] = React.useState();
  const [whereone, setWhereone] = React.useState('');
  const [wheretwo, setWheretwo] = React.useState('');
  const [wherethree, setWherethree] = React.useState('');
  const [from, setFrom] = React.useState('');
  const [contains, setContains] = React.useState('');
  const [groupby, setGroupby] = React.useState('');
  const [tableData, setTableData] = React.useState();
  
  const [bgColor, setBgColor] = React.useState("1d1f21");
  const options = [
    {value: "o", name: "All"},
    {value: "o/data[at0001]/events[at0006]/data[at0003]/items[at0004]", name: "Systolic Blood Pressure"},
    {value: "o/data[at0001]/events[at0006]/data[at0003]/items[at0005]", name: "Diastolic Blood Pressure"},
    {value: "o/data[at0001]/events[at0006]/data[at0003]/items[at0033]", name: "Comments"},
    {value: "o/name/value", name: "Author Name"},
    ]

  const handleSelect = (option) => {
    setSelect(option);
  };
  const handleWhereOne = (event) => {
    setWhereone(event.target.value);
  };
  const handleWhereTwo = (event) => {
    setWheretwo(event.target.value);
  };
  const handleWhereThree = (event) => {
    setWherethree(event.target.value);
  };
  const handleContains = (event) => {
    setContains(event.target.value);
  };
  const handleFrom = (event) => {
    setFrom(event.target.value);
  };
  const handleGroupBy = (event) => {
    setGroupby(event.target.value);
  };
  React.useEffect(() => {
    fetch('https://api.unsplash.com/photos/random/?client_id=y7WEACJVLxo4M0KOSnQJxJa36IHUWOxhe5XZ6wF37FY&query=healthcare')
       .then((response) => response.json())
       .then((data) => {
          console.log(data.urls.regular);
          setBg(data.urls.regular);
       })
       .catch((err) => {
          console.log(err.message);
       });
   }, []);
  var loadTable = () => {
    const query = "select o/data[at0001]/events[at1042]/data[at0003] from ehr e contains observation o";
    const addPosts = async () => {
      await fetch('http://localhost:8080/ehrbase/rest/openehr/v1/query/aql', {
      method: 'POST',
      body: JSON.stringify({
         "q": query,
      }),
      headers: {
         'Content-type': 'application/json; charset=UTF-8',
      },
      })
      .then((response) =>response.json())
      .then((data) => {
         setResult(data);
        //  console.log(data);
        var myTable = [];
        for(var row of result.rows){
          for(var item of row[0].items){
            myTable.push({'name':item.name.value,'value':item.value,'path':`o/data[at0001]/events[at1042]/data[at0003]/items[${item['archetype_node_id']}]`})
          }
        }
        console.log(myTable)
        setTableData(myTable)
      })
      .catch((err) => {
         console.log(err.message);
      });
      };
    addPosts();
    setBgColor("000000");
    console.log(result)
    setLoading(false);
    setChecked(true);

  }
  const handleSubmit = (event) => {
    setLoading(true);
    event.preventDefault();
    const data = new FormData(event.currentTarget);  
    setQuery(data.get('query'));
    setQueryName(data.get('query_name'));
    var selected_options = []
    for (let option of select){
      selected_options.push(option.value)
    }
    const new_select = selected_options.join(',')
    const queryy = "select " + new_select + from + contains + whereone + wheretwo + wherethree + groupby;
    console.log(new_select);
    const addPosts = async () => {
      await fetch('http://localhost:8080/ehrbase/rest/openehr/v1/query/aql', {
      method: 'POST',
      body: JSON.stringify({
         "q": queryy,
      }),
      headers: {
         'Content-type': 'application/json; charset=UTF-8',
      },
      })
      .then((response) =>response.json())
      .then((data) => {
         setResult(data);
        //  console.log(data);
      })
      .catch((err) => {
         console.log(err.message);
      });
      };
    addPosts();
    setBgColor("1d1f21");
    setLoading(false);
    setChecked(true);
  };
  
  
  const handleSaveQuery = () => {
        // const myq = {[queryName]:query};
        // localStorage.setItem("queries",JSON.stringify(myq));  
    
    const queries = JSON.parse(localStorage.getItem("queries")); 
    if(localStorage.getItem("queries")){
        queries[queryName] = query; 
        localStorage.setItem("queries",JSON.stringify(queries));  
    }else{
        const myq = {[queryName]:query};
        localStorage.setItem("queries",JSON.stringify(myq));  
    }
    console.log(queries);
  }
  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' ,overflow:'hidden'}}>
        <CssBaseline />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              overflow: 'hidden',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            
            <a href="/">
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <HealthAndSafetyIcon />
            </Avatar>
            </a>
            <Typography component="h1" variant="h5">
              Saral Anuyojan
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
                <Box sx={{ minWidth: 150 }}>
                    <FormControl sx={{ m: 1 }} fullWidth>
                        {/* <InputLabel id="demo-simple-select-label">Select</InputLabel> */}
                        {/* <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={select}
                        label="Select"
                        > */}
                        <Multiselect
                          labelId="demo-simple-select-label"
                          id="demo-simple-select"
                          label="Select"
                          options={options} // Options to display in the dropdown
                          selectedValues={select} // Preselected value to persist in dropdown
                          onSelect={handleSelect} // Function will trigger on select event
                          displayValue="name" // Property name to display in the dropdown options
                        />
                        {/* <MenuItem value={"select o"}>Observations</MenuItem>
                        <MenuItem value={"select c"}>Compositions</MenuItem>
                        <MenuItem value={"select o/data[at0001]/events[at0006]/data[at0003]/items[at0004]"}>Systolic Blood Pressure</MenuItem>
                        <MenuItem value={"select o/data[at0001]/events[at0006]/data[at0003]/items[at0005]"}>Diastolic Blood Pressure</MenuItem>
                        <MenuItem value={"select o/data[at0001]/events[at0006]/data[at0003]/items[at0033]"}>Comments</MenuItem>
                        <MenuItem value={"select o/name/value"}>Author Name</MenuItem> */}
                        {/* </Select> */}
                    </FormControl>
                </Box>
                <Box sx={{ minWidth: 150 }} >
                    <FormControl sx={{ m: 1 }} fullWidth>
                        <InputLabel id="demo-simple-select-label">From</InputLabel>
                        <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={from}
                        label="From"
                        onChange={handleFrom}
                        >
                        <MenuItem value={" from EHR e"}>EHR- be658bf3-8550-42ab-a90d-fd52584e127c</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
                <Box sx={{ minWidth: 150 }} >
                    <FormControl sx={{ m: 1 }} fullWidth>
                        <InputLabel id="demo-simple-select-label">Contains</InputLabel>
                        <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={contains}
                        label="Contains"
                        onChange={handleContains}
                        >
                        <MenuItem value={" contains observation o"}>Observations</MenuItem>
                        <MenuItem value={" contains composition c"}>Compositions</MenuItem>
                        <MenuItem value={" contains observation o and composition c"}>Both</MenuItem>
                        <MenuItem value={""}>Nested Query</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
                <Box sx={{ minWidth: 150 }}>
                    <FormControl sx={{ m: 1, minWidth: 160 }}>
                        <InputLabel id="demo-simple-select-label">Where</InputLabel>
                        <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={whereone}
                        label="Where"
                        onChange={handleWhereOne}
                        >
                        <MenuItem value={""}></MenuItem>
                        <MenuItem value={" where o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude"}>Systolic Blood Pressure</MenuItem>
                        <MenuItem value={" where o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude"}>Diastolic Blood Pressure</MenuItem>
                        <MenuItem value={" where o/data[at0001]/events[at0006]/data[at0003]/items[at0033]/value/value"}>Comments</MenuItem>
                        <MenuItem value={" where o/name/value"}>Author Name</MenuItem>
                        </Select>
                    </FormControl>
                    <FormControl sx={{ m: 1, minWidth: 60 }}>
                        <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={wheretwo}
                        label=""
                        onChange={handleWhereTwo}
                        >
                        <MenuItem value={""}></MenuItem>
                        <MenuItem value={" == "}>{'=='}</MenuItem>
                        <MenuItem value={" >= "}>{'>='}</MenuItem>
                        <MenuItem value={" <= "}>{'<='}</MenuItem>
                        <MenuItem value={" > "}>{'>'}</MenuItem>
                        <MenuItem value={" < "}>{'<'}</MenuItem>
                        </Select>
                    </FormControl>
                    <TextField
                        sx={{ m: 1, minWidth: 60 }}
                        margin="normal"
                        name="query_name"
                        label=""
                        id="query_name"
                        value={wherethree}
                        onChange={handleWhereThree}
                    />
                </Box>
                <Box sx={{ minWidth: 150 }}>
                    <FormControl sx={{ m: 1 }} fullWidth>
                        <InputLabel id="demo-simple-select-label">Order By</InputLabel>
                        <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={groupby}
                        label="Group By"
                        onChange={handleGroupBy}
                        >
                        <MenuItem value={""}></MenuItem>
                        <MenuItem value={" order by c/context/start_time"}>Time</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ m:1 }}
              >
                Submit Query
              </Button>
              <div onClick={handleSaveQuery}>
              <Button
              type="submit"
                fullWidth
                variant="contained"
                sx={{ m:1 }}
              >
                Save Query
              </Button>
              </div>
              <Button
                onClick = {() => {loadTable();}}
                fullWidth
                variant="contained"
                sx={{ m:1 }}
              >
                Load Table
              </Button>
              <a href="/savedquery">Saved Queries</a>
            </Box>
          </Box>
        </Grid>
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${bg})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        >
          
          {loading ? <CircularProgress /> : 
          <Slide direction="up" in={checked} container={containerRef.current}>
                
                <Paper sx={{ 
                    my: 8,
                    mx: 4,
                    overflow: 'scroll',
                    height: '500px',
                    backgroundColor: bgColor,
                    opacity:'10' }} elevation={4}>
                  <Box>
                  <Grid container spacing={2}>
                    <Grid item xs={11}>
                      <Typography variant='h5' align='center' color='1d1f21' style={{ paddingTop: '10px'}}>Output</Typography>
                    </Grid>
                    <Grid item xs={1}>         
                        <IconButton onClick={() => {setChecked(false);}}>
                          <Close sx={{color: "#acaeb0"}} elevation={4} ></Close>
                        </IconButton>
                    </Grid>
                  </Grid>
                  
                    {bgColor == "1d1f21" ? (<Typography align='left' color='white' sx={{ paddingTop: '20px',paddingLeft: '20px',}}>
                        <ReactJson src={result} theme="tomorrow"/>
                        {/* <JSONTree data={result} />; */}
                        {/* <Graph json={JSON.parse(result)}/> */}
                    </Typography>):(<JsonToTable json={tableData}></JsonToTable>)}
                  </Box>
                </Paper>
          </Slide>}
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}